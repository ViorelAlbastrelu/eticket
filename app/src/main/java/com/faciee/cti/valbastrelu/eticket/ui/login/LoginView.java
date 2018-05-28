package com.faciee.cti.valbastrelu.eticket.ui.login;

import com.faciee.cti.valbastrelu.eticket.main.ETicketApp;

public interface LoginView {
	
	default ETicketApp getETicketApplication(){return ETicketApp.getCurrentETicketApp();}
	
	void signIn();
	void createAccount();
	void goToHomeActivity();
	
	void setEmailViewInvalid(boolean isInvalid);
	void setPasswordViewInvalid(boolean isInvalid);
	
	void showToast(String message);
}
