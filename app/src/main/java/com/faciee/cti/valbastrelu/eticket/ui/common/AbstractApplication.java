package com.faciee.cti.valbastrelu.eticket.ui.common;

import android.app.Application;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.faciee.cti.valbastrelu.eticket.repo.ETkRepository;
import com.faciee.cti.valbastrelu.eticket.room.EtkRoomDB;

public abstract class AbstractApplication extends Application {
	
	protected static AbstractApplication currentApplication;
	
	public static AbstractApplication getCurrentApplication(){
		return currentApplication;
	}
	
	public static String getStringResource(@StringRes int resourceId){
		return currentApplication.getString(resourceId);
	}
	
	public static void toastMessage(String message, int duration){
		Toast.makeText(getCurrentApplication(), message, duration).show();
		
	}public static void toastMessageShort(String message){
		Toast.makeText(getCurrentApplication(), message, Toast.LENGTH_SHORT).show();
	}
	public static void toastMessageLong(String message){
		Toast.makeText(getCurrentApplication(), message, Toast.LENGTH_LONG).show();
	}
	
	public EtkRoomDB getDatabase() {
		return EtkRoomDB.getDatabase(getCurrentApplication());
	}
	
	public ETkRepository getRepository() {
		return ETkRepository.getInstance(getDatabase());
	}
}
