package com.faciee.cti.valbastrelu.eticket.extensions

import android.app.Application
import android.widget.Toast
import androidx.annotation.StringRes

fun Application.getStringResource(@StringRes resourceId: Int) =
		this.getString(resourceId)

fun Application.toastMessage(message: String, duration: Int) {
	Toast.makeText(this, message, duration).show()
}

fun Application.toastMessageShort(message: String) {
	Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Application.toastMessageLong(message: String) {
	Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}