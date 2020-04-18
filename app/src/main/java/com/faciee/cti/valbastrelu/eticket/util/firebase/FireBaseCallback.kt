package com.faciee.cti.valbastrelu.eticket.util.firebase

interface FireBaseCallback {
	fun signInSuccessfully()
	fun registeredSuccessfully()
	fun failWithError(message: String)
}