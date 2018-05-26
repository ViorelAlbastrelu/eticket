package com.faciee.cti.valbastrelu.eticket.ui.chat;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.ChatMessageAdapter;
import com.faciee.cti.valbastrelu.eticket.util.AppUtils;

import org.alicebot.ab.AIMLProcessor;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.Graphmaster;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.PCAIMLProcessorExtension;
import org.alicebot.ab.Timer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;

public class Chatbot extends AppCompatActivity {
	
	@BindView(R.id.chatView) ListView mListView;
	@BindView(R.id.send_message) EditText mEditTextMessage;
	
	//chatbot
	private Chat chat;
	private ChatMessageAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chatbot);
		ButterKnife.bind(this);
		mEditTextMessage.setImeOptions(EditorInfo.IME_ACTION_DONE);
		mAdapter = new ChatMessageAdapter(this, new ArrayList<>());
		mListView.setAdapter(mAdapter);
		new CongfigureBotTask().execute();
	}
	
	@OnClick(R.id.btn_send)
	void sendOnButtonTap(){
		sendMessage();
	}
	
	@OnEditorAction(R.id.send_message)
	boolean sendInEditor(TextView v, int actionId, KeyEvent event){
		boolean handled = false;
		if (actionId == EditorInfo.IME_ACTION_DONE) {
			sendMessage();
			handled = true;
		}
		return handled;
	}
	
	private void sendMessage() {
		String message = mEditTextMessage.getText().toString();
		ChatMessage chatMessage = new ChatMessage(message, true, false);
		String response = chat.multisentenceRespond(mEditTextMessage.getText().toString());
		if (TextUtils.isEmpty(message)) {
			return;
		}
		mAdapter.add(chatMessage);
		mEditTextMessage.setText("");
		mimicOtherMessage(response);
		mListView.setSelection(mAdapter.getCount() - 1);
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
	
	private class CongfigureBotTask extends AsyncTask<Void, Void, Bot>{
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		
		@Override
		protected Bot doInBackground(Void... voids) {
			//receiving the assets from the app directory
			AssetManager assets = getResources().getAssets();
			File jayDir = new File(getFilesDir() + "/asuka/bots/Asuka");
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
			MagicStrings.root_path = getFilesDir() + "/asuka";
			System.out.println("Working Directory = " + MagicStrings.root_path);
			AIMLProcessor.extension =  new PCAIMLProcessorExtension();
			//Assign the AIML files to bot for processing
			return new Bot("Asuka", MagicStrings.root_path, "chat");
		}
		
		@Override
		protected void onPostExecute(Bot bot) {
			chat = new Chat(bot);
			super.onPostExecute(bot);
		}
	}
}
