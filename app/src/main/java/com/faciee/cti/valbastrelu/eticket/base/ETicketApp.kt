package com.faciee.cti.valbastrelu.eticket.base

import android.app.Application
import android.content.Context
import android.util.Log
import com.faciee.cti.valbastrelu.eticket.extensions.toastMessageShort
import com.faciee.cti.valbastrelu.eticket.repo.ETkBusRepository
import com.faciee.cti.valbastrelu.eticket.repo.EtkParkingRepository
import com.faciee.cti.valbastrelu.eticket.room.EtkRoomDB
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class ETicketApp : Application() {
	var appPreferences: ETkAppPreferences? = null
		private set
	var firebaseAuth: FirebaseAuth
		private set
	val database: EtkRoomDB?
		get() = EtkRoomDB.getDatabase(currentETicketApp)

	val busRepository: ETkBusRepository
		get() = ETkBusRepository.getInstance(database)

	val parkingRepository: EtkParkingRepository
		get() = EtkParkingRepository.getInstance(database)

	init {
		currentETicketApp = this
		firebaseAuth = FirebaseAuth.getInstance()
	}

	override fun onCreate() {
		super.onCreate()
		Log.d(TAG, "onCreate: $TAG")
		appPreferences = ETkAppPreferences(getSharedPreferences(ETK_SHARED_PREF_KEY, Context.MODE_PRIVATE))
	}

	companion object {
		//TODO remove this one once all the classes are converted to Kotlin
		@JvmStatic
		fun toastMessageShort(message: String) {
			currentETicketApp.toastMessageShort(message)
		}
		@JvmStatic
		fun getStringResource(resId: Int): String = currentETicketApp.getString(resId)

		@JvmStatic
		fun getDefaultLanguage(): String = Locale.getDefault().language

		private const val TAG = "ETicketApp"
		private const val ETK_SHARED_PREF_KEY = "ETicketApp"

		@JvmStatic
		lateinit var currentETicketApp: ETicketApp
			private set
	}
}