package com.faciee.cti.valbastrelu.eticket.ui.common;

import com.faciee.cti.valbastrelu.eticket.ui.bus.i.FragmentViewI;

public class AbstractFrgTabPresenter<FWI extends FragmentViewI> {
	
	protected FWI fragmentViewI;
	
	protected AbstractFrgTabPresenter(FWI fragmentViewI) {
		this.fragmentViewI = fragmentViewI;
	}
}
