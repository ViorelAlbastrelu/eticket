package com.faciee.cti.valbastrelu.eticket.ui.login;

import android.text.TextUtils;

public class LoginPresenter implements LoginPresenterI{
	private LoginView loginView;
	private FireBaseWrapper fireBaseAuth;
	
	
	public LoginPresenter(LoginView loginView) {
		this.loginView = loginView;
	}
	
	
	public void executeLoginAction(String email, String password) {
		if (validateForm(email, password)) return;
		fireBaseAuth.signIn(email, password);
	}
	
	public void executeRegisterAction(String email, String password) {
		if (validateForm(email, password)) return;
		fireBaseAuth.register(email, password);
	}
	
	@Override
	public boolean validateForm(String email, String password) {
		boolean invalid = false;
		if (TextUtils.isEmpty(email) || !email.contains("@")) {
			loginView.setEmailViewInvalid(true);
			invalid = true;
		} else loginView.setEmailViewInvalid(false);
		
		if (TextUtils.isEmpty(password) || password.length() < 6) {
			loginView.setPasswordViewInvalid(true);
			invalid = true;
		} else loginView.setPasswordViewInvalid(false);
		return invalid;
	}
	
	public void checkUser() {
		if (fireBaseAuth.getCurrentUser() != null) loginView.goToHomeActivity();
	}
	
	@Override
	public void updateSignInView() {
		loginView.signIn();
	}
	
	@Override
	public void updateCreateAccount() {
		loginView.createAccount();
	}
	
	@Override
	public void showToast(String message) {
		loginView.showToast(message);
	}
	
	public void setFireBaseAuth(FireBaseWrapper fireBaseAuth) {
		this.fireBaseAuth = fireBaseAuth;
	}
}
