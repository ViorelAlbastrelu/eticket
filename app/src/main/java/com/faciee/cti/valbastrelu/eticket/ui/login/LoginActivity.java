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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.faciee.cti.valbastrelu.eticket.ETicketActivity;
import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.main.ETicketApp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView{
	private static final String TAG = "LoginActivity";
	private LoginPresenter loginPresenter;
	
	// UI references.
	@BindView(R.id.email) AutoCompleteTextView mEmailView;
	@BindView(R.id.password) EditText mPasswordView;
	@BindView(R.id.login_progress) ProgressBar mProgressView;
	@BindView(R.id.login_form) View mLoginFormView;
	@BindView(R.id.statusTextView) TextView mStatusTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		loginPresenter = new LoginPresenter(this);
		ButterKnife.bind(this);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		loginPresenter.checkUser();
		//TODO setari de test
		mEmailView.setText("test@email.com");
		mPasswordView.setText("123456");
	}
	
	@OnClick(R.id.btn_autentificare)
	protected void login() {
		loginPresenter.executeLoginAction(mEmailView.getText().toString(), mPasswordView.getText().toString());
	}
	
	@OnClick(R.id.btn_inregistrare)
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
		Intent goToHome = new Intent(LoginActivity.this, ETicketActivity.class);
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
}

