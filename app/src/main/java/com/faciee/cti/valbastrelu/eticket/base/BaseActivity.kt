package com.faciee.cti.valbastrelu.eticket.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
	val eTicketApp: ETicketApp
		get() = ETicketApp.currentETicketApp
}