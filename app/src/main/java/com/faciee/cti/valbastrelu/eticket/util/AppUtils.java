package com.faciee.cti.valbastrelu.eticket.util;

import android.content.res.AssetManager;
import android.os.Environment;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

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

	// filename = "transurb_itinerar.json"
	public static String loadJSONFromAsset(AssetManager assets, String fileName) {
		String json = null;
		try {
			InputStream is = assets.open(fileName);
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			json = new String(buffer, StandardCharsets.UTF_8);
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		return json;
	}

	public static <C> C getObjectFromJSON(String json, Class<C> type){
		return new Gson().fromJson(json, type);
	}
}
