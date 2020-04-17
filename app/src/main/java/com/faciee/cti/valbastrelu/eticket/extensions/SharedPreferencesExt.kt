package com.faciee.cti.valbastrelu.eticket.extensions

import android.content.SharedPreferences

/**
 * Created by valbastrelu on 09-Apr-18.
 */
//getters
fun SharedPreferences.getSafeString(key: String): String = this.getString(key, "") ?: ""

fun SharedPreferences.getSafeInt(key: String): Int = this.getInt(key, 0)

fun SharedPreferences.getSafeBoolean(key: String): Boolean = this.getBoolean(key, false)

fun SharedPreferences.getSafeFloat(key: String): Float = this.getFloat(key, 0f)

fun SharedPreferences.getSafeLong(key: String): Long = this.getLong(key, 0L)

fun SharedPreferences.getSafeStringSet(key: String): Set<String> = this.getStringSet(key, setOf()) ?: setOf()

//setters
fun SharedPreferences.putValueAndApply(key: String, value: Any) {
	this.edit().apply {
		when (value) {
			is String -> putString(key, value)
			is Int -> putInt(key, value)
			is Float -> putFloat(key, value)
			is Boolean -> putBoolean(key, value)
		}
		apply()
	}
}

fun SharedPreferences.putSetAndApply(key: String, value: Set<String>) {
	this.edit().apply {
		putStringSet(key, value)
		apply()
	}
}

//clean
fun SharedPreferences.removeKeys(vararg keys: String?) {
	this.edit().apply {
		keys.forEach { key ->
			remove(key)
		}
		apply()
	}
}

fun SharedPreferences.clearAll() {
	this.edit().apply {
		clear()
		apply()
	}
}