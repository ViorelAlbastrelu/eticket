package com.faciee.cti.valbastrelu.eticket.ui.main;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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

public class Chatbot extends AppCompatActivity {
	
	private ListView mListView;
	private Button mButtonSend;
	private EditText mEditTextMessage;
	private ChatMessageAdapter mAdapter;
	
	//chatbot
	private Bot bot;
	private static Chat chat;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chatbot);
		mListView = (ListView) findViewById(R.id.listView);
		mButtonSend = (Button) findViewById(R.id.btn_send);
		mEditTextMessage = (EditText) findViewById(R.id.et_message);
		mAdapter = new ChatMessageAdapter(this, new ArrayList<ChatMessage>());
		mListView.setAdapter(mAdapter);

		//code for sending the message
		mButtonSend.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String message = mEditTextMessage.getText().toString();
				//bot
				String response = chat.multisentenceRespond(mEditTextMessage.getText().toString());
				if (TextUtils.isEmpty(message)) {
					return;
				}
				sendMessage(message);
				mimicOtherMessage(response);
				mEditTextMessage.setText("");
				mListView.setSelection(mAdapter.getCount() - 1);
			}
		});
		
		chatbotConfig();
	}
	
	private void chatbotConfig() {
		//checking SD card availablility
		boolean a = AppUtils.isSDCARDAvailable();
//receiving the assets from the app directory
		AssetManager assets = getResources().getAssets();
		File jayDir = new File(Environment.getExternalStorageDirectory().toString() + "/asuka/bots/Asuka");
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
		MagicStrings.root_path = Environment.getExternalStorageDirectory().toString() + "/asuka";
		System.out.println("Working Directory = " + MagicStrings.root_path);
		AIMLProcessor.extension =  new PCAIMLProcessorExtension();
		//Assign the AIML files to bot for processing
		bot = new Bot("Asuka", MagicStrings.root_path, "chat");
		chat = new Chat(bot);
		String[] args = null;
		mainFunction(args);
	}
	
	private void sendMessage(String message) {
		ChatMessage chatMessage = new ChatMessage(message, true, false);
		mAdapter.add(chatMessage);
	}
	
	private void mimicOtherMessage(String message) {
		ChatMessage chatMessage = new ChatMessage(message, false, false);
		mAdapter.add(chatMessage);
	}
	
	private void sendMessage() {
		ChatMessage chatMessage = new ChatMessage(null, true, true);
		mAdapter.add(chatMessage);
		
		mimicOtherMessage();
	}
	
	private void mimicOtherMessage() {
		ChatMessage chatMessage = new ChatMessage(null, false, true);
		mAdapter.add(chatMessage);
	}
	

	//Request and response of user and the bot
	public static void mainFunction (String[] args) {
		MagicBooleans.trace_mode = false;
		System.out.println("trace mode = " + MagicBooleans.trace_mode);
		Graphmaster.enableShortCuts = true;
		Timer timer = new Timer();
		String request = "Hello.";
		String response = chat.multisentenceRespond(request);
		
		System.out.println("Human: "+request);
		System.out.println("Robot: " + response);
	}
}
