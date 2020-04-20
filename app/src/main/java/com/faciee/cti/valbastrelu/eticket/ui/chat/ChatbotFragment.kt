package com.faciee.cti.valbastrelu.eticket.ui.chat

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
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
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.ChatMessageAdapter
import com.faciee.cti.valbastrelu.eticket.util.AppUtils.copyFile
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
import java.util.*

class ChatbotFragment : Fragment() {

	private lateinit var chatbotBinding: FragmentChatbotBinding
	private lateinit var mAdapter: ChatMessageAdapter

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		chatbotBinding = FragmentChatbotBinding.inflate(inflater, container, false)

		//init views
		chatbotBinding.btnSend.setOnClickListener { view: View? -> sendMessage() }
		chatbotBinding.sendMessage.imeOptions = EditorInfo.IME_ACTION_DONE
		chatbotBinding.sendMessage.setOnEditorActionListener { v: TextView?, actionId: Int, event: KeyEvent? -> sendInEditor(v, actionId, event) }
		mAdapter = ChatMessageAdapter(context, ArrayList())
		chatbotBinding.chatView.adapter = mAdapter
		CongfigureBotTask(context).execute()
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
		val response = chat !!.multisentenceRespond(message)
		if (TextUtils.isEmpty(message)) {
			return
		}
		mAdapter.add(chatMessage)
		chatbotBinding.sendMessage.setText("")
		mimicOtherMessage(response)
		chatbotBinding.chatView.setSelection(mAdapter.count - 1)
	}

	private fun mimicOtherMessage(message: String) {
		val chatMessage = ChatMessage(message, false, false)
		mAdapter.add(chatMessage)
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
	private class CongfigureBotTask internal constructor(@field:SuppressLint("StaticFieldLeak") var context: Context?) : AsyncTask<Void?, Void?, Bot>() {

		override fun doInBackground(vararg voids: Void?): Bot { //receiving the assets from the app directory
			val assets = context !!.resources.assets
			val jayDir = File(context !!.filesDir.toString() + "/asuka/bots/Asuka")
			val b = jayDir.mkdirs()
			if (jayDir.exists()) { //Reading the file
				try {
					for (dir in assets.list("asuka")) {
						val subdir = File(jayDir.path + "/" + dir)
						val subdir_check = subdir.mkdirs()
						for (file in assets.list("asuka/$dir")) {
							val f = File(jayDir.path + "/" + dir + "/" + file)
							if (f.exists()) {
								continue
							}
							var `in`: InputStream? = null
							var out: OutputStream? = null
							`in` = assets.open("asuka/$dir/$file")
							out = FileOutputStream(jayDir.path + "/" + dir + "/" + file)
							//copy file from assets to the mobile's SD card or any secondary memory
							copyFile(`in`, out)
							`in`.close()
							`in` = null
							out.flush()
							out.close()
							out = null
						}
					}
				} catch (e: IOException) {
					e.printStackTrace()
				}
			}
			//get the working directory
			MagicStrings.root_path = context !!.filesDir.toString() + "/asuka"
			println("Working Directory = " + MagicStrings.root_path)
			AIMLProcessor.extension = PCAIMLProcessorExtension()
			//Assign the AIML files to bot for processing
			return Bot("Asuka", MagicStrings.root_path, "chat")
		}

		override fun onPostExecute(bot: Bot) {
			chat = Chat(bot)
			if (context != null) context = null
			super.onPostExecute(bot)
		}

	}

	companion object {
		//chatbot
		private var chat: Chat? = null
	}
}