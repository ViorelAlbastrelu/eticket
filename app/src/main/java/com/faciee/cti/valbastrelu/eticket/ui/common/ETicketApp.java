package com.faciee.cti.valbastrelu.eticket.ui.common;

import android.content.Context;
import android.util.Log;

import com.faciee.cti.valbastrelu.eticket.main.ETkAppPreferences;

public class ETicketApp extends AbstractApplication {
	private static final String TAG = "ETicketApp";
	private static final String ETK_SHARED_PREF_KEY = "ETicketApp";
	private ETkAppPreferences eTkAppPreferences;
	
	public ETicketApp() {
		ETicketApp.currentApplication = this;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "onCreate: " + TAG);
		eTkAppPreferences = new ETkAppPreferences(getSharedPreferences(ETK_SHARED_PREF_KEY, Context.MODE_PRIVATE));
	}
	
	public static ETicketApp getCurrentETicketApp() {
		return (ETicketApp) currentApplication;
	}
	
	public ETkAppPreferences getAppPreferences() {
		return eTkAppPreferences;
	}
}
