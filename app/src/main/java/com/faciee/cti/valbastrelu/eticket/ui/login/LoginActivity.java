package com.faciee.cti.valbastrelu.eticket.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.databinding.ActivityLoginBinding;
import com.faciee.cti.valbastrelu.eticket.main.ETicketApp;
import com.faciee.cti.valbastrelu.eticket.main.ETicketMain;
import com.faciee.cti.valbastrelu.eticket.main.ETkAppPreferences;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity implements LoginView{
	private static final String TAG = "LoginActivity";
	private LoginPresenter loginPresenter;
	private ActivityLoginBinding loginBinding;
	
	// UI references.
	AutoCompleteTextView mEmailView;
	EditText mPasswordView;
	ProgressBar mProgressView;
	View mLoginFormView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
		setContentView(loginBinding.getRoot());
		loginPresenter = new LoginPresenter(this);
		loginPresenter.setFireBaseAuth(new FireBaseWrapper(loginPresenter)); //TODO look into this crap
		initViews();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		loginPresenter.checkUser();
		//TODO setari de test
		mEmailView.setText("test@email.com");
		mPasswordView.setText("123456");
	}
	
	protected void login() {
		loginPresenter.executeLoginAction(mEmailView.getText().toString(), mPasswordView.getText().toString());
		ETkAppPreferences preferences = ETicketApp.getCurrentETicketApp().getAppPreferences();
		preferences.setCurrentEmail(mEmailView.getText().toString());
	}
	
	protected void contNou() {
		loginPresenter.executeRegisterAction(mEmailView.getText().toString(), mPasswordView.getText().toString());
	}
	
	@Override
	public void signIn() {
		Log.d(TAG, "signIn: ");
		mLoginFormView.setVisibility(View.GONE);
		goToHomeActivity();
	}
	
	@Override
	public void createAccount() {
		Log.d(TAG, "createAccount: ");
		ETicketApp.toastMessageShort("Cont creat cu succes!");
		goToHomeActivity();
	}
	
	
	@Override
	public void goToHomeActivity() {
		//TODO goToHomeActivity(FirebaseUser user) - add user to intent bundle and send throughout activity
		mProgressView.setVisibility(View.GONE);
		Intent goToHome = new Intent(LoginActivity.this, ETicketMain.class);
		this.finish();
		startActivity(goToHome);
	}
	
	@Override
	public void setEmailViewInvalid(boolean isInvalid) {
		mEmailView.setError(isInvalid ? ETicketApp.getStringResource(R.string.error_invalid_email) : null);
	}
	
	@Override
	public void setPasswordViewInvalid(boolean isInvalid) {
		mPasswordView.setError(isInvalid ? ETicketApp.getStringResource(R.string.error_invalid_password) : null);
	}
	
	@Override
	public void showToast(String message) {
		ETicketApp.toastMessageShort(message);
	}

	private void initViews(){
		mEmailView = loginBinding.email;
		mPasswordView = loginBinding.password;
		mProgressView = loginBinding.loginProgress;
		mLoginFormView = loginBinding.loginForm;

		loginBinding.btnAutentificare.setOnClickListener(v -> login());
		loginBinding.btnInregistrare.setOnClickListener(v -> contNou());
	}

	private void updateUI(FirebaseUser user) {
		mEmailView.setText(user.getEmail());
		if (user != null) {
			loginBinding.statusTextView.setText(getString(R.string.emailpassword_status_fmt,
												user.getEmail(),
												user.isEmailVerified()));
			loginBinding.statusTextView.setVisibility(View.VISIBLE);
			
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
}

