package com.faciee.cti.valbastrelu.eticket.base

import android.app.Activity

abstract class BaseActivity : Activity() {
	val eTicketApp: ETicketApp
		get() = ETicketApp.currentETicketApp
}