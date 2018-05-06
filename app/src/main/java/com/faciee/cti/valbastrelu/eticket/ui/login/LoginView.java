package com.faciee.cti.valbastrelu.eticket.ui.login;

import com.faciee.cti.valbastrelu.eticket.main.ETicketApp;

public interface LoginView {
	
	void goToHomeActivity();
	default ETicketApp getETicketApplication(){return ETicketApp.getCurrentETicketApp();}
}
