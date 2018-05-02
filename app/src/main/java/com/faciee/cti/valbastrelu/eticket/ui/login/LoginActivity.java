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
import android.widget.Toast;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.ui.bus.BusActivity;
import com.faciee.cti.valbastrelu.eticket.ui.common.ETicketApp;
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
	@BindView(R.id.email) private AutoCompleteTextView mEmailView;
	@BindView(R.id.password) private EditText mPasswordView;
	@BindView(R.id.login_progress) private View mProgressView;
	@BindView(R.id.login_form) private View mLoginFormView;
	
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
	}
	
	@OnClick(R.id.btn_autentificare)
	private void login() {
		signIn(mEmailView.getText().toString(), mPasswordView.getText().toString());
	}
	
	@OnClick(R.id.btn_inregistrare)
	private void contNou() {
		createAccount(mEmailView.getText().toString(), mPasswordView.getText().toString());
	}
	
	private void signIn(String email, String password) {
		Log.d(TAG, "signIn: " + email);
		if (!validateForm()) return;
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
		if (!validateForm()) return;
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
	
	private void updateUI(FirebaseUser user) {
	/*	hideProgressDialog();
		if (user != null) {
			mStatusTextView.setText(getString(R.string.emailpassword_status_fmt,
					user.getEmail(), user.isEmailVerified()));
			mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));
			
			findViewById(R.id.email_password_buttons).setVisibility(View.GONE);
			findViewById(R.id.email_password_fields).setVisibility(View.GONE);
			findViewById(R.id.signed_in_buttons).setVisibility(View.VISIBLE);
			
			findViewById(R.id.verify_email_button).setEnabled(!user.isEmailVerified());
		} else {
			mStatusTextView.setText(R.string.signed_out);
			mDetailTextView.setText(null);
			
			findViewById(R.id.email_password_buttons).setVisibility(View.VISIBLE);
			findViewById(R.id.email_password_fields).setVisibility(View.VISIBLE);
			findViewById(R.id.signed_in_buttons).setVisibility(View.GONE);
		}*/
	}
	
	private boolean validateForm() {
		boolean valid = true;
		String email = mEmailView.getText().toString();
		if (TextUtils.isEmpty(email)) {
			mEmailView.setError("Required.");
			valid = false;
		} else mEmailView.setError(null);
		
		String password = mPasswordView.getText().toString();
		if (TextUtils.isEmpty(password)) {
			mPasswordView.setError("Required.");
			valid = false;
		} else mPasswordView.setError(null);
		return valid;
	}
	
	private void signOut() {
		mAuth.signOut();
	}
	
	@Override
	public void goToHomeActivity() {
		//TODO goToHomeActivity(FirebaseUser user) - add user to intend bundle and send throudh activity
		Intent goToHomeActivity = new Intent(LoginActivity.this, BusActivity.class);
		this.finish();
		startActivity(goToHomeActivity);
	}
}

