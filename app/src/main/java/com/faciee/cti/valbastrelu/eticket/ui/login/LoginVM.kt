package com.faciee.cti.valbastrelu.eticket.ui.login

import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.faciee.cti.valbastrelu.eticket.base.ETicketApp
import com.faciee.cti.valbastrelu.eticket.util.firebase.FireBaseClient

class LoginVM(
		private val eTicketApp: ETicketApp,
		private val fireBaseClient: FireBaseClient) :
		AndroidViewModel(eTicketApp) {

	private var email: String? = null
	var loading = MutableLiveData<Boolean>()
	var emailValidation = MutableLiveData<Boolean>()
	var passwordValidation = MutableLiveData<Boolean>()

	fun checkUser() {
		fireBaseClient.checkUser()
	}

	fun loginWithCredentials(email: String, password: String) {
		if (!isFormValid(email, password)) return
		this.email = email
		fireBaseClient.signIn(email, password)
	}

	fun registerNewUser(email: String, password: String) {
		if (!isFormValid(email, password)) return
		this.email = email
		fireBaseClient.register(email, password)
	}

	fun saveUserInSession(){
		email?.let { eTicketApp.appPreferences.setCurrentEmail(it) }
	}

	fun isFormValid(email: String, password: String): Boolean {
		var invalid = false
		if (TextUtils.isEmpty(email) || android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
			emailValidation.value = INVALID_EMAIL
			invalid = INVALID_EMAIL
		}
		if (TextUtils.isEmpty(password) || password.length < PASSWORD_LENGTH) {
			passwordValidation.value = INVALID_PASSWORD
			invalid = INVALID_PASSWORD
		}
		return invalid
	}

	companion object {
		const val INVALID_EMAIL = true
		const val INVALID_PASSWORD = true
		const val PASSWORD_LENGTH = 6

		fun getFactory(eTicketApp: ETicketApp, fireBaseClient: FireBaseClient) = object : ViewModelProvider.Factory {
			@Suppress("UNCHECKED_CAST")
			override fun <T : ViewModel?> create(modelClass: Class<T>): T {
				return LoginVM(eTicketApp, fireBaseClient) as T
			}
		}
	}
}