package com.faciee.cti.valbastrelu.eticket.util;

import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by valbastrelu on 09-Apr-18.
 */

public interface SharedPreferencesUtil {
	
	//getters
	static String getString(SharedPreferences sharedPreferences, String key) {
		return (!sharedPreferences.contains(key)) ? null :
				sharedPreferences.getString(key, null);
	}
	
	static Integer getInt(SharedPreferences sharedPreferences, String key) {
		return (!sharedPreferences.contains(key)) ? null :
				sharedPreferences.getInt(key, 0);
	}
	
	static Boolean getBoolean(SharedPreferences sharedPreferences, String key) {
		return (!sharedPreferences.contains(key)) ? null :
				sharedPreferences.getBoolean(key, false);
	}
	
	static Float getFloat(SharedPreferences sharedPreferences, String key) {
		return (!sharedPreferences.contains(key)) ? null :
				sharedPreferences.getFloat(key, 0);
	}
	
	static Long getLong(SharedPreferences sharedPreferences, String key) {
		return (!sharedPreferences.contains(key)) ? null :
				sharedPreferences.getLong(key, 0);
	}
	
	static Set<String> getStringSet(SharedPreferences sharedPreferences, String key) {
		return (!sharedPreferences.contains(key)) ? null :
				sharedPreferences.getStringSet(key, new HashSet<String>());
	}
	
	//setters
	static void putValueInSharedPref(SharedPreferences sharedPreferences, String key, String value) {
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	static void putValueInSharedPref(SharedPreferences sharedPreferences, String key, int value) {
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}
	
	static void putValueInSharedPref(SharedPreferences sharedPreferences, String key, float value) {
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putFloat(key, value);
		editor.commit();
	}
	
	static void putValueInSharedPref(SharedPreferences sharedPreferences, String key, Boolean value) {
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
	
	//clean
	static void removeKeys(SharedPreferences sharedPreferences, String... keys) {
		SharedPreferences.Editor editor = sharedPreferences.edit();
		for (String key : keys)
			editor.remove(key);
		editor.commit();
	}
	
	static void clearAll(SharedPreferences sharedPreferences){
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.clear();
		editor.commit();
	}
}
