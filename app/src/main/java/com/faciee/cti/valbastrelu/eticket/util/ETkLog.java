package com.faciee.cti.valbastrelu.eticket.util;

import android.content.Context;
import android.support.annotation.Nullable;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ETkLog {
	private static final String TAG = "ETkLog";
	private static final String LOG_FILE = "eticket_log.txt";
	private FileOutputStream stream;
//	private static String week = String.valueOf(new Date().getDay());
	
	public ETkLog(Context context) {
		try {
			stream = context.openFileOutput(LOG_FILE, Context.MODE_APPEND);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
//		File logFile = new File(context.getFilesDir(), LOG_FILE);
	}
	
	public void f(String tag, String message, @Nullable Exception e){
		try {
			stream.write(message.getBytes());
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}finally {
			if (stream!=null) {
				try {
					stream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
