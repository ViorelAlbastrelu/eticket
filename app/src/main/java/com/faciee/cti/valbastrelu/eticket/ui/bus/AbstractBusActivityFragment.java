package com.faciee.cti.valbastrelu.eticket.ui.bus;

import android.support.v4.app.Fragment;

import com.faciee.cti.valbastrelu.eticket.ui.bus.i.FragmentViewI;
import com.faciee.cti.valbastrelu.eticket.ui.bus.presenter.BusPresenter;

abstract class AbstractBusActivityFragment extends Fragment implements FragmentViewI{
	
	
	//TODO add getBusPresenter and make the field private
	@Override
	public BusActivity getBusActivity() {
		return (BusActivity) getActivity();
	}
	
	@Override
	public BusPresenter getBusPresenter() {
		return getBusActivity().busPresenter;
	}
}
