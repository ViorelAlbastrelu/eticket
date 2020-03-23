package com.faciee.cti.valbastrelu.eticket.ui.chat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.databinding.ActivityChatbotBinding;
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.ChatMessageAdapter;
import com.faciee.cti.valbastrelu.eticket.util.AppUtils;

import org.alicebot.ab.AIMLProcessor;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.PCAIMLProcessorExtension;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


public class ChatbotActivity extends AppCompatActivity {

	private ActivityChatbotBinding chatbotBinding;
	
	private ListView chatList;
	private EditText messageInput;

	//chatbot
	private static Chat chat;
	private ChatMessageAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		chatbotBinding = ActivityChatbotBinding.inflate(getLayoutInflater());
		setContentView(chatbotBinding.getRoot());
		//init views
		chatList = chatbotBinding.chatView;
		messageInput = chatbotBinding.sendMessage;

		chatbotBinding.btnSend.setOnClickListener(view -> sendMessage());
		messageInput.setImeOptions(EditorInfo.IME_ACTION_DONE);
		messageInput.setOnEditorActionListener(this::sendInEditor);
		mAdapter = new ChatMessageAdapter(this, new ArrayList<>());
		chatList.setAdapter(mAdapter);
//		toolbar.setTitle(R.string.menu_assistant);
		new CongfigureBotTask(this).execute();
	}

	boolean sendInEditor(TextView v, int actionId, KeyEvent event){
		boolean handled = false;
		if (actionId == EditorInfo.IME_ACTION_DONE) {
			sendMessage();
			handled = true;
		}
		return handled;
	}
	
	private void sendMessage() {
		String message = messageInput.getText().toString().trim();
		ChatMessage chatMessage = new ChatMessage(message, true, false);
		String response = chat.multisentenceRespond(message);
		if (TextUtils.isEmpty(message)) {
			return;
		}
		mAdapter.add(chatMessage);
		messageInput.setText("");
		mimicOtherMessage(response);
		chatList.setSelection(mAdapter.getCount() - 1);
	}
	
	private void mimicOtherMessage(String message) {
		ChatMessage chatMessage = new ChatMessage(message, false, false);
		mAdapter.add(chatMessage);
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
	
	private static class CongfigureBotTask extends AsyncTask<Void, Void, Bot>{
		
		@SuppressLint("StaticFieldLeak")
		Context context;
		
		CongfigureBotTask(Context context) {
			this.context = context;
		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		
		@Override
		protected Bot doInBackground(Void... voids) {
			//receiving the assets from the app directory
			AssetManager assets = context.getResources().getAssets();
			File jayDir = new File(context.getFilesDir() + "/asuka/bots/Asuka");
			boolean b = jayDir.mkdirs();
			if (jayDir.exists()) {
				//Reading the file
				try {
					for (String dir : assets.list("asuka")) {
						File subdir = new File(jayDir.getPath() + "/" + dir);
						boolean subdir_check = subdir.mkdirs();
						for (String file : assets.list("asuka/" + dir)) {
							File f = new File(jayDir.getPath() + "/" + dir + "/" + file);
							if (f.exists()) {
								continue;
							}
							InputStream in = null;
							OutputStream out = null;
							in = assets.open("asuka/" + dir + "/" + file);
							out = new FileOutputStream(jayDir.getPath() + "/" + dir + "/" + file);
							//copy file from assets to the mobile's SD card or any secondary memory
							AppUtils.copyFile(in, out);
							in.close();
							in = null;
							out.flush();
							out.close();
							out = null;
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			//get the working directory
			MagicStrings.root_path = context.getFilesDir() + "/asuka";
			System.out.println("Working Directory = " + MagicStrings.root_path);
			AIMLProcessor.extension =  new PCAIMLProcessorExtension();
			//Assign the AIML files to bot for processing
			return new Bot("Asuka", MagicStrings.root_path, "chat");
		}
		
		@Override
		protected void onPostExecute(Bot bot) {
			chat = new Chat(bot);
			if (context != null) context = null;
			super.onPostExecute(bot);
		}
	}
}
