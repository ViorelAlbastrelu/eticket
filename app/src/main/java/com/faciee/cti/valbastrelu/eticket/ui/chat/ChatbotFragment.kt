package com.faciee.cti.valbastrelu.eticket.ui.chat

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.faciee.cti.valbastrelu.eticket.databinding.FragmentChatbotBinding
import com.faciee.cti.valbastrelu.eticket.util.AppUtils.copyFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.alicebot.ab.AIMLProcessor
import org.alicebot.ab.Bot
import org.alicebot.ab.Chat
import org.alicebot.ab.MagicStrings
import org.alicebot.ab.PCAIMLProcessorExtension
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

const val CHATBOT_NAME_ASUKA = "asuka"

class ChatbotFragment : Fragment() {

	private lateinit var chat: Chat
	private lateinit var chatbotBinding: FragmentChatbotBinding
	private lateinit var chatMessageAdapter: ChatMessagesAdapter

	override fun onAttach(context: Context) {
		super.onAttach(context)
		chatMessageAdapter = ChatMessagesAdapter()
		CoroutineScope(IO).launch {
			val chatBot = configureChatBot(context)
			initChatInstance(chatBot)
		}
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		chatbotBinding = FragmentChatbotBinding.inflate(inflater, container, false)

		//init views
		chatbotBinding.btnSend.setOnClickListener { view: View? -> sendMessage() }
		chatbotBinding.sendMessage.imeOptions = EditorInfo.IME_ACTION_DONE
		chatbotBinding.sendMessage.setOnEditorActionListener { v: TextView?, actionId: Int, event: KeyEvent? -> sendInEditor(v, actionId, event) }
		chatbotBinding.chatView.adapter = chatMessageAdapter

		return chatbotBinding.root
	}

	fun sendInEditor(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
		var handled = false
		if (actionId == EditorInfo.IME_ACTION_DONE) {
			sendMessage()
			handled = true
		}
		return handled
	}

	private fun sendMessage() {
		val message = chatbotBinding.sendMessage.text.toString().trim { it <= ' ' }
		val chatMessage = ChatMessage(message, true, false)
		val response = chat.multisentenceRespond(message)
		if (TextUtils.isEmpty(message)) {
			return
		}
		chatMessageAdapter.add(chatMessage)
		chatbotBinding.sendMessage.setText("")
		mimicOtherMessage(response)
//		chatbotBinding.chatView.setSelection(chatMessageAdapter.count - 1)
	}

	private fun mimicOtherMessage(message: String) {
		val chatMessage = ChatMessage(message, false, false)
		chatMessageAdapter.add(chatMessage)
	}

	//Request and response of user and the bot
//	public static void mainFunction (String[] args) {
//		MagicBooleans.trace_mode = false;
//		System.out.println("trace mode = " + MagicBooleans.trace_mode);
//		Graphmaster.enableShortCuts = true;
//		Timer timer = new Timer();
//		String request = "Hello.";
//		String response = chat.multisentenceRespond(request);
//
//		System.out.println("Human: "+request);
//		System.out.println("Robot: " + response);
//	}

	private suspend fun configureChatBot(context: Context): Bot {
		//receiving the assets from the app directory
		withContext(IO) {

			val assets = context.resources.assets
			val jayDir = File("${context.filesDir}/$CHATBOT_NAME_ASUKA/bots/Asuka")
			val jaydir_check = jayDir.mkdirs()
			if (jayDir.exists()) { //Reading the file
				try {
					for (dir in assets.list("$CHATBOT_NAME_ASUKA")) {
						val subdir = File("${jayDir.path}/$dir")
						val subdir_check = subdir.mkdirs()
						for (file in assets.list("$CHATBOT_NAME_ASUKA/$dir")) {
							val f = File("${jayDir.path}/$dir/$file")
							if (f.exists()) {
								continue
							}
							val inputS: InputStream = assets.open("$CHATBOT_NAME_ASUKA/$dir/$file")
							val outputS: OutputStream = FileOutputStream("${jayDir.path}/$dir/$file")
							//copy file from assets to the mobile's SD card or any secondary memory
							copyFile(inputS, outputS)
							inputS.close()
							outputS.flush()
							outputS.close()
						}
					}
				} catch (e: IOException) {
					e.printStackTrace()
				}
			}
			//get the working directory
			MagicStrings.root_path = context.filesDir.toString() + "/$CHATBOT_NAME_ASUKA"
			println("Working Directory = " + MagicStrings.root_path)
			AIMLProcessor.extension = PCAIMLProcessorExtension()
			//Assign the AIML files to bot for processing
		}
		return Bot("Asuka", MagicStrings.root_path, "chat")
	}

	private suspend fun initChatInstance(bot: Bot) {
		withContext(Main) {
			chat = Chat(bot)
		}
	}
}