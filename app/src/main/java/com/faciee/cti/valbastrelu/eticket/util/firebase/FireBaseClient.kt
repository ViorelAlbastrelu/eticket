package com.faciee.cti.valbastrelu.eticket.util.firebase

import com.faciee.cti.valbastrelu.eticket.base.ETicketApp
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FireBaseClient(private val callback: FireBaseCallback) {
	private val firebaseAuth: FirebaseAuth = ETicketApp.currentETicketApp.firebaseAuth

	val currentUser: FirebaseUser?
		get() = firebaseAuth.currentUser

	fun checkUser(){
		if (currentUser == null) callback.failWithError("No user in session")
	}

	fun signIn(email: String, password: String) {
		firebaseAuth.signInWithEmailAndPassword(email, password)
				.addOnCompleteListener { task: Task<AuthResult?> ->
					if (task.isSuccessful) {
						callback.signInSuccessfully()
					} else {
						callback.failWithError("Authentication failed")
					}
				}
	}

	fun register(email: String, password: String) {
		firebaseAuth.createUserWithEmailAndPassword(email, password)
				.addOnCompleteListener { task ->
					if (task.isSuccessful) {
						val user = firebaseAuth.currentUser
						callback.registeredSuccessfully()
					} else {
						callback.failWithError("Authentication failed")
					}
				}
	}

}