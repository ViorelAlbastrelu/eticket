package com.faciee.cti.valbastrelu.eticket.base

import androidx.appcompat.app.AppCompatActivity
import com.faciee.cti.valbastrelu.eticket.ui.login.LoginActivity

abstract class BaseActivity : AppCompatActivity() {
	val eTicketApp: ETicketApp
		get() = ETicketApp.currentETicketApp

	fun signout(){
		eTicketApp.firebaseAuth.signOut()
		startActivity(LoginActivity.prepareIntent(this))
		//TODO keep main app in background and add flag in LoginActivity to intent SINGLE_TOP
		finish()
	}
}