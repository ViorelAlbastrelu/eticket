package com.faciee.cti.valbastrelu.eticket.main;

import android.content.SharedPreferences;

import static com.faciee.cti.valbastrelu.eticket.util.SharedPreferencesUtil.clearAll;
import static com.faciee.cti.valbastrelu.eticket.util.SharedPreferencesUtil.getString;
import static com.faciee.cti.valbastrelu.eticket.util.SharedPreferencesUtil.putValueInSharedPref;
import static com.faciee.cti.valbastrelu.eticket.util.SharedPreferencesUtil.removeKeys;

/**
 * Created by valbastrelu on 09-Apr-18.
 */

public class ETkAppPreferences implements IETkAppPreferences {
	
	private static final String CURRENT_EMAIL = "EMAIL";
	
	private SharedPreferences sharedPreferences;
	
	public ETkAppPreferences(SharedPreferences sharedPreferences){
		this.sharedPreferences = sharedPreferences;
	}
	
	void clearAllPrefs(){
		clearAll(sharedPreferences);
	}
	
	@Override
	public void cleanPreferences() {
		removeKeys(sharedPreferences, CURRENT_EMAIL);
	}
	
	@Override
	public String getCurrentEmail() {
		return getString(sharedPreferences, CURRENT_EMAIL);
	}
	
	@Override
	public void setCurrentEmail(String email) {
		putValueInSharedPref(sharedPreferences, CURRENT_EMAIL, email);
	}
}
