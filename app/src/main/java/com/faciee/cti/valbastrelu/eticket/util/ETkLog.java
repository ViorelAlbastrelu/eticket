package com.faciee.cti.valbastrelu.eticket.util;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class ETkLog {
	private static final String TAG = "ETkLog";
	private static final String LOG_FILE = "eticket_log.txt";
	private static FileOutputStream stream;

	public static void f(String tag, String message, Context context) {
		Log.d(TAG, "f: writing stream to path " + context.getFilesDir());
		String time = String.valueOf(Calendar.getInstance().getTime());
		String line = String.format("%s %s : %s \n", time, tag, message);
		try {
			stream = context.openFileOutput(LOG_FILE, Context.MODE_APPEND);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
		try {
			stream.write(line.getBytes());
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
