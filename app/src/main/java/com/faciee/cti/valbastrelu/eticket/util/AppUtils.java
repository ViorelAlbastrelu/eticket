package com.faciee.cti.valbastrelu.eticket.util;

import android.os.Environment;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AppUtils {
	//check SD card availability
	public static boolean isSDCARDAvailable(){
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}
	//copying the file
	public static void copyFile(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int read;
		while((read = in.read(buffer)) != -1){
			out.write(buffer, 0, read);
		}
	}
}
