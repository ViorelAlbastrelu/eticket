package com.faciee.cti.valbastrelu.eticket.base

/**
 * Created by valbastrelu on 09-Apr-18.
 */
interface IETkAppPreferences {
	fun cleanPreferences()
	fun getCurrentEmail(): String
	fun setCurrentEmail(email: String)
}