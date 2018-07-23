package com.faciee.cti.valbastrelu.eticket.ui.parking;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.generated.callback.OnClickListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.databinding.ParkingFrag01BiletBinding;
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.BiletParkingRVAdapter;
import com.faciee.cti.valbastrelu.eticket.ui.parking.model.ParkingActivityModel;

public class FrgTb01Bilet extends Fragment {
	private static final String TAG = "FrgTb01Bilet";
	
	private ParkingFrag01BiletBinding binding;
	private ParkingActivityModel sharedModel;
	private BiletParkingRVAdapter rvAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		binding = DataBindingUtil.inflate(inflater, R.layout.parking_frag01_bilet, container, false);
		rvAdapter = new BiletParkingRVAdapter();
		binding.recyclerViewBileteP.setAdapter(rvAdapter);
		return binding.getRoot();
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		sharedModel = ViewModelProviders.of(getActivity()).get(ParkingActivityModel.class);
		subscribeUI(sharedModel);
	}
	
	private void subscribeUI(ParkingActivityModel model) {
		model.getLiveDataBilete().observe(this, list -> {
			if (list != null) {
				binding.setIsLoading(false);
				rvAdapter.setBilete(list);
			} else binding.setIsLoading(true);
			binding.executePendingBindings();
		});
	}
	
}
