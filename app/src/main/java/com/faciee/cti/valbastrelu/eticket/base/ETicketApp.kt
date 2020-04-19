package com.faciee.cti.valbastrelu.eticket.base

import android.app.Application
import android.content.Context
import android.util.Log
import com.faciee.cti.valbastrelu.eticket.extensions.toastMessageShort
import com.faciee.cti.valbastrelu.eticket.repo.ETkBusRepository
import com.faciee.cti.valbastrelu.eticket.repo.EtkParkingRepository
import com.faciee.cti.valbastrelu.eticket.room.EtkRoomDB
import com.faciee.cti.valbastrelu.eticket.room.SingletonHolder
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class ETicketApp : Application() {
	lateinit var appPreferences: ETkAppPreferences
		private set
	lateinit var firebaseAuth: FirebaseAuth
		private set
	lateinit var database: EtkRoomDB
		private set

	val busRepository: ETkBusRepository?
		get() = database.let { ETkBusRepository.getInstance(it) }

	val parkingRepository: EtkParkingRepository?
		get() = database.let { EtkParkingRepository.getInstance(it) }

	init {
		currentETicketApp = this
	}

	override fun onCreate() {
		super.onCreate()
		Log.d(TAG, "onCreate: $TAG")
		appPreferences = ETkAppPreferences(getSharedPreferences(ETK_SHARED_PREF_KEY, Context.MODE_PRIVATE))
		firebaseAuth = FirebaseAuth.getInstance()
		database = EtkRoomDB.getInstance(this.applicationContext)

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