package com.faciee.cti.valbastrelu.eticket.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.faciee.cti.valbastrelu.eticket.BuildConfig
import com.faciee.cti.valbastrelu.eticket.R
import com.faciee.cti.valbastrelu.eticket.base.BaseActivity
import com.faciee.cti.valbastrelu.eticket.base.ETicketApp.Companion.getStringResource
import com.faciee.cti.valbastrelu.eticket.base.ETicketApp.Companion.toastMessageShort
import com.faciee.cti.valbastrelu.eticket.databinding.ActivityLoginBinding
import com.faciee.cti.valbastrelu.eticket.util.firebase.FireBaseCallback
import com.faciee.cti.valbastrelu.eticket.util.firebase.FireBaseClient
import com.google.firebase.auth.FirebaseUser

class LoginActivity : BaseActivity(), FireBaseCallback {

	private lateinit var viewModel: LoginVM
	private lateinit var loginBinding: ActivityLoginBinding

	// UI references.
	private var mEmailView: AutoCompleteTextView? = null
	private var mPasswordView: EditText? = null
	private var mProgressView: ProgressBar? = null
	private var mLoginFormView: View? = null


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		viewModel = ViewModelProvider(this, LoginVM.getFactory(eTicketApp, FireBaseClient(this))).get(LoginVM::class.java)
		loginBinding = ActivityLoginBinding.inflate(layoutInflater)
		setContentView(loginBinding.root)
		initViews()
	}

	override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
		val view = super.onCreateView(name, context, attrs)
		viewModel.emailValidation.observe(this, Observer {invalid ->
			loginBinding.email.error = if (invalid) getStringResource(R.string.error_invalid_email) else null
		})
		viewModel.passwordValidation.observe(this, Observer {invalid ->
			loginBinding.password.error = if (invalid) getStringResource(R.string.error_invalid_password) else null
		})
		return view
	}

	override fun onStart() {
		super.onStart()
		viewModel.checkUser()
		if (BuildConfig.DEBUG){
			loginBinding.email.setText("test@email.com")
			loginBinding.password.setText("123456")
		}
	}

	private fun goToHomeActivity() {
		//TODO goToHomeActivity(FirebaseUser user) - add user to intent bundle and send throughout activity
		mProgressView !!.visibility = View.GONE
		finish()
	}

	override fun signInSuccessfully() {
		goToHomeActivity()
	}

	override fun registeredSuccessfully() {
		goToHomeActivity()
	}

	override fun failWithError(message: String) {
		toastMessageShort(message)
	}


	private fun initViews() {
		mEmailView = loginBinding.email
		mPasswordView = loginBinding.password
		mProgressView = loginBinding.loginProgress
		mLoginFormView = loginBinding.loginForm
		loginBinding.btnAutentificare.setOnClickListener {
			viewModel.loginWithCredentials(loginBinding.email.text.toString(), loginBinding.password.text.toString())
		}
		loginBinding.btnInregistrare.setOnClickListener {
			viewModel.registerNewUser(loginBinding.email.text.toString(), loginBinding.password.text.toString())
		}
	}

	private fun updateUI(user: FirebaseUser?) {
		mEmailView !!.setText(user !!.email)
		if (user != null) {
			loginBinding.statusTextView.text = getString(R.string.emailpassword_status_fmt,
					user.email,
					user.isEmailVerified)
			loginBinding.statusTextView.visibility = View.VISIBLE
			//			mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));
//			findViewById(R.id.email_password_buttons).setVisibility(View.GONE);
//			findViewById(R.id.email_password_fields).setVisibility(View.GONE);
//			findViewById(R.id.verify_email_button).setEnabled(!user.isEmailVerified());
		} else { //			mStatusTextView.setText(R.string.signed_out);
//			mDetailTextView.setText(null);
//
//			findViewById(R.id.email_password_buttons).setVisibility(View.VISIBLE);
//			findViewById(R.id.email_password_fields).setVisibility(View.VISIBLE);
//			findViewById(R.id.signed_in_buttons).setVisibility(View.GONE);
		}
	}

	private fun login() {

	}

	companion object {
		private const val TAG = "LoginActivity"

		fun prepareIntent(activity: Activity) = Intent(activity, LoginActivity::class.java)
	}
}