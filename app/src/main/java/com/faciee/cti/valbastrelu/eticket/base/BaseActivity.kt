package com.faciee.cti.valbastrelu.eticket.base

import androidx.appcompat.app.AppCompatActivity
import com.faciee.cti.valbastrelu.eticket.ui.login.LoginActivity

abstract class BaseActivity : AppCompatActivity() {
	val eTicketApp: ETicketApp
		get() = ETicketApp.currentETicketApp

	fun signout(){
		eTicketApp.firebaseAuth.signOut()
		eTicketApp.appPreferences.clearUserPreferences()
		startActivity(LoginActivity.prepareIntent(this))
		finish()
	}
}