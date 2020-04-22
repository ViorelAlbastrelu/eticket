package com.faciee.cti.valbastrelu.eticket.base

import android.content.SharedPreferences
import com.faciee.cti.valbastrelu.eticket.extensions.clearAll
import com.faciee.cti.valbastrelu.eticket.extensions.getSafeString
import com.faciee.cti.valbastrelu.eticket.extensions.putValueAndApply
import com.faciee.cti.valbastrelu.eticket.extensions.removeKeys

/**
 * Created by valbastrelu on 09-Apr-18.
 */
class ETkAppPreferences(private val sharedPreferences: SharedPreferences) : IETkAppPreferences {
	fun clearAllPrefs() {
		sharedPreferences.clearAll()
	}

	override fun cleanPreferences() {
		sharedPreferences.removeKeys(EMAIL_KEY)
	}

	override fun getCurrentEmail(): String = sharedPreferences.getSafeString(EMAIL_KEY)

	override fun setCurrentEmail(email: String) {
		sharedPreferences.putValueAndApply(EMAIL_KEY, email)
	}

	companion object {
		private const val EMAIL_KEY = "EMAIL"
	}
}