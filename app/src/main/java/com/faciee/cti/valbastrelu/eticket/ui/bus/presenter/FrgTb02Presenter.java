package com.faciee.cti.valbastrelu.eticket.ui.bus.presenter;

import android.view.View;

import com.faciee.cti.valbastrelu.eticket.ui.bus.i.FragmentPresenterI;
import com.faciee.cti.valbastrelu.eticket.ui.bus.i.FragmentViewI;
import com.faciee.cti.valbastrelu.eticket.ui.bus.model.BusActivityModel;

public class FrgTb02Presenter extends AbstractFrgTabPresenter implements FragmentPresenterI {
	
	private FragmentViewI fragmentViewI;
	private BusActivityModel busActivityModel;
	
	public FrgTb02Presenter(FragmentViewI fragmentViewI) {
		this.fragmentViewI = fragmentViewI;
		this.busActivityModel = new BusActivityModel();
	}
	
	public void populateRecylerView(View view) {
		fragmentViewI.buildRecyclerView(view, busActivityModel.getListaTrasee());
	}
}
