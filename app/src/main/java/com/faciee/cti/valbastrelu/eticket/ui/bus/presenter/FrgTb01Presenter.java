package com.faciee.cti.valbastrelu.eticket.ui.bus.presenter;

import android.view.View;

import com.faciee.cti.valbastrelu.eticket.ui.bus.model.BusActivityModel;
import com.faciee.cti.valbastrelu.eticket.ui.bus.i.FragmentPresenterI;
import com.faciee.cti.valbastrelu.eticket.ui.bus.i.FragmentViewI;
import com.faciee.cti.valbastrelu.eticket.ui.bus.model.AbstractActivityModel;

public class FrgTb01Presenter extends AbstractFrgTabPresenter<FragmentViewI,AbstractActivityModel> implements FragmentPresenterI {
	
	private FragmentViewI fragmentViewI;
	private BusActivityModel busActivityModel;
	
	public FrgTb01Presenter(FragmentViewI fragmentViewI) {
		this.fragmentViewI = fragmentViewI;
		this.busActivityModel = new BusActivityModel();
	}
	
	public void populateRecylerView(View view) {
		fragmentViewI.buildRecyclerView(view, busActivityModel.getListaBilete());
	}
}
