package com.faciee.cti.valbastrelu.eticket.ui.login;

public interface LoginPresenterI {
	
	boolean validateForm(String email, String password);
	
	void updateSignInView();
	
	void updateCreateAccount();
	
	void showToast(String message);
}
