package com.faciee.cti.valbastrelu.eticket.ui.common;

import com.faciee.cti.valbastrelu.eticket.ui.bus.model.BusActivityModel;

public class AbstractFrgTabPresenter<FragmentViewI> {
	
	protected FragmentViewI fragmentViewI;
	
	protected AbstractFrgTabPresenter(FragmentViewI fragmentViewI) {
		this.fragmentViewI = fragmentViewI;
	}
}
