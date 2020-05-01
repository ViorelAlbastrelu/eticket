package com.faciee.cti.valbastrelu.eticket.base

import android.content.SharedPreferences
import com.faciee.cti.valbastrelu.eticket.extensions.clearAll
import com.faciee.cti.valbastrelu.eticket.extensions.getSafeBoolean
import com.faciee.cti.valbastrelu.eticket.extensions.getSafeString
import com.faciee.cti.valbastrelu.eticket.extensions.putValueAndApply
import com.faciee.cti.valbastrelu.eticket.extensions.removeKeys

/**
 * Created by valbastrelu on 09-Apr-18.
 */
class ETkAppPreferences(private val sharedPreferences: SharedPreferences) {
	fun clearAllPrefs() {
		sharedPreferences.clearAll()
	}

	fun cleanPreferences() {
		sharedPreferences.removeKeys(EMAIL_KEY)
	}

	var currentEmail: String
		set(value) = sharedPreferences.putValueAndApply(EMAIL_KEY, value)
		get() = sharedPreferences.getSafeString(EMAIL_KEY)

	var scheduleCache: Boolean
		set(value) = sharedPreferences.putValueAndApply(SCHEDULE_CACHE, value)
		get() = sharedPreferences.getSafeBoolean(SCHEDULE_CACHE)

	companion object {
		private const val EMAIL_KEY = "EMAIL"
		private const val SCHEDULE_CACHE = "SCHEDULE_CACHE"
	}
}