package com.faciee.cti.valbastrelu.eticket.ui.login;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FireBaseWrapper {
	private FirebaseAuth mAuth;
	private LoginPresenterI presenterI;
	
	public FireBaseWrapper(LoginPresenterI presenterI) {
		this.mAuth = FirebaseAuth.getInstance();
		this.presenterI = presenterI;
	}
	
	public void signIn(String email, String password) {
		mAuth.signInWithEmailAndPassword(email, password)
				.addOnCompleteListener(task -> {
					if (task.isSuccessful()){
						FirebaseUser user = mAuth.getCurrentUser();
						presenterI.updateSignInView();
					}else {
						presenterI.showToast("Authentication failed");
					}
				});
	}
	
	public void register(String email, String password) {
		mAuth.createUserWithEmailAndPassword(email, password)
				.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						if (task.isSuccessful()) {
							FirebaseUser user = mAuth.getCurrentUser();
							presenterI.updateCreateAccount();
//							updateUI(user);
						} else {
							presenterI.showToast("Authentication failed");
//							updateUI(null);
						}
					}
				});
	}
	
	public FirebaseUser getCurrentUser() {
		return mAuth.getCurrentUser();
	}
}
