package com.faciee.cti.valbastrelu.eticket.main;

import android.content.SharedPreferences;

/**
 * Created by valbastrelu on 09-Apr-18.
 */

public class ETkAppPreferences implements IETkAppPreferences {
	
	private SharedPreferences sharedPreferences;
	
	ETkAppPreferences(SharedPreferences sharedPreferences){
		this.sharedPreferences = sharedPreferences;
	}
	
	
	
	
	@Override
	public void cleanPreferences() {
	
	}
}
