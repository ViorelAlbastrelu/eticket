package com.faciee.cti.valbastrelu.eticket.ui.parking;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.ui.parking.model.ParkingActivityModel;

public class FrgTb01Bilet extends Fragment {
	private static final String TAG = "FrgTb01Bilet";
	
	public FrgTb01Bilet() {
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		return inflater.inflate(R.layout.parking_frag01_bilet, container, false);
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		final ParkingActivityModel model =
				ViewModelProviders.of(getActivity()).get(ParkingActivityModel.class);
		subscribeUI(model);
	}
	
	private void subscribeUI(ParkingActivityModel model) {
	}
}
