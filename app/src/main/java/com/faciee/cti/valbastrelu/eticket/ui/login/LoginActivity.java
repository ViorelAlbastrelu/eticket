package com.faciee.cti.valbastrelu.eticket.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.ui.bus.BusActivity;
import com.faciee.cti.valbastrelu.eticket.main.ETicketApp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoginView{
	private static final String TAG = "LoginActivity";
	private FirebaseAuth mAuth;
	
	// UI references.
	@BindView(R.id.email) AutoCompleteTextView mEmailView;
	@BindView(R.id.password) EditText mPasswordView;
	@BindView(R.id.login_progress) View mProgressView;
	@BindView(R.id.login_form) View mLoginFormView;
	@BindView(R.id.statusTextView) TextView mStatusTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ButterKnife.bind(this);
		mAuth = FirebaseAuth.getInstance();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		FirebaseUser currentUser = mAuth.getCurrentUser();
		
		//TODO setari de test
		mEmailView.setText("test@email.com");
	}
	
	@OnClick(R.id.btn_autentificare)
	protected void login() {
		signIn(mEmailView.getText().toString(), mPasswordView.getText().toString());
	}
	
	@OnClick(R.id.btn_inregistrare)
	protected void contNou() {
		createAccount(mEmailView.getText().toString(), mPasswordView.getText().toString());
	}
	
	private void signIn(String email, String password) {
		Log.d(TAG, "signIn: " + email);
		if (validateForm()) return;
		mAuth.signInWithEmailAndPassword(email, password)
				.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						if (task.isSuccessful()){
							Log.d(TAG, "onComplete: signInWithEmailAndPassword success");
							FirebaseUser user = mAuth.getCurrentUser();
							goToHomeActivity();
						}else {
							Log.w(TAG, "onComplete: signInWithEmailAndPassword failure", task.getException());
							ETicketApp.toastMessageShort("Authentication failed");
						}
					}
				});
	}
	
	
	private void createAccount(String email, String password) {
		Log.d(TAG, "createAccount: " + email);
		if (validateForm()) return;
		mAuth.createUserWithEmailAndPassword(email, password)
				.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						if (task.isSuccessful()) {
							Log.d(TAG, "onComplete: createUserWithEmailAndPassword : success");
							FirebaseUser user = mAuth.getCurrentUser();
							updateUI(user);
						} else {
							Log.w(TAG, "onComplete: createUserWithEmailAndPassword : failure", task.getException());
							ETicketApp.toastMessageShort("Authentication failed");
							updateUI(null);
						}
					}
				});
	}
	
	
	@Override
	public void goToHomeActivity() {
		//TODO goToHomeActivity(FirebaseUser user) - add user to intent bundle and send through activity
		Intent goToHome = new Intent(LoginActivity.this, BusActivity.class);
		this.finish();
		startActivity(goToHome);
	}
	
	private boolean validateForm() {
		boolean invalid = false;
		String email = mEmailView.getText().toString();
		if (TextUtils.isEmpty(email) && email.contains("@")) {
			mEmailView.setError(ETicketApp.getStringResource(R.string.error_invalid_email));
			invalid = true;
		} else mEmailView.setError(null);
		
		String password = mPasswordView.getText().toString();
		
			if (TextUtils.isEmpty(password)) {
				mPasswordView.setError(ETicketApp.getStringResource(R.string.error_incorrect_password));
				if (password.length() >= 6)
					mPasswordView.setError(ETicketApp.getStringResource(R.string.error_invalid_password));
				invalid = false;
			} else {
				mPasswordView.setError(null);
			}
		return invalid;
	}
	
	private void updateUI(FirebaseUser user) {
		mEmailView.setText(user.getEmail());
		if (user != null) {
			mStatusTextView.setText(getString(R.string.emailpassword_status_fmt,
												user.getEmail(),
												user.isEmailVerified()));
			mStatusTextView.setVisibility(View.VISIBLE);
			
//			mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));
//			findViewById(R.id.email_password_buttons).setVisibility(View.GONE);
//			findViewById(R.id.email_password_fields).setVisibility(View.GONE);
//			findViewById(R.id.verify_email_button).setEnabled(!user.isEmailVerified());
		} else {
//			mStatusTextView.setText(R.string.signed_out);
//			mDetailTextView.setText(null);
//
//			findViewById(R.id.email_password_buttons).setVisibility(View.VISIBLE);
//			findViewById(R.id.email_password_fields).setVisibility(View.VISIBLE);
//			findViewById(R.id.signed_in_buttons).setVisibility(View.GONE);
		}
	}
	
	private void signOut() {
		mAuth.signOut();
	}
}

