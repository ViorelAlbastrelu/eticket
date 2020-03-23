package com.faciee.cti.valbastrelu.eticket.ui.parking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.databinding.ParkingFrag01BiletBinding;
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.BiletParkingRVAdapter;
import com.faciee.cti.valbastrelu.eticket.ui.common.i.UpdateRecyclerViewCallback;
import com.faciee.cti.valbastrelu.eticket.ui.parking.model.ParkingViewModel;

public class FrgTb01Bilet extends Fragment {
	private static final String TAG = "FrgTb01Bilet";
	
	private ParkingFrag01BiletBinding binding;
	private ParkingViewModel sharedModel;
	private BiletParkingRVAdapter rvAdapter;
	
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		binding = DataBindingUtil.inflate(inflater, R.layout.parking_frag01_bilet, container, false);
		rvAdapter = new BiletParkingRVAdapter();
		binding.recyclerViewBileteP.setAdapter(rvAdapter);
		return binding.getRoot();
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		sharedModel = new ViewModelProvider(this).get(ParkingViewModel.class);
		subscribeUI();
	}
	
	private void subscribeUI() {
		sharedModel.getLiveDataBilete().observe(getViewLifecycleOwner(), list -> {
			if (list != null) {
				binding.setIsLoading(false);
				rvAdapter.setBilete(list);
			} else binding.setIsLoading(true);
			binding.executePendingBindings();
		});
	}
	
	private final UpdateRecyclerViewCallback recyclerViewCallback = new UpdateRecyclerViewCallback() {
		@Override
		public void scrollToTop() {
			binding.recyclerViewBileteP.scrollTo(0, 0);
		}
	};
	
	public UpdateRecyclerViewCallback getRecycleViewCallback() {
		return recyclerViewCallback;
	}
}
