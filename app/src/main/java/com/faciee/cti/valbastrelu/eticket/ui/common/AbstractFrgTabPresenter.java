package com.faciee.cti.valbastrelu.eticket.ui.bus.presenter;

import com.faciee.cti.valbastrelu.eticket.ui.bus.model.BusActivityModel;

class AbstractFrgTabPresenter<FragmentViewI> {
	
	FragmentViewI fragmentViewI;
	BusActivityModel busActivityModel;
	
	AbstractFrgTabPresenter(FragmentViewI fragmentViewI) {
		this.fragmentViewI = fragmentViewI;
		this.busActivityModel = new BusActivityModel();
	}
}
