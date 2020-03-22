package com.faciee.cti.valbastrelu.eticket.ui.bus;

import androidx.lifecycle.ViewModelProviders;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.databinding.BusFrag01BiletBinding;
import com.faciee.cti.valbastrelu.eticket.ui.bus.model.BusViewModel;
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.BiletRVAdapter;

/**
 * Created by valbastrelu on 09-Apr-18.
 */

public class FrgTb01Bilet extends Fragment{
	private static final String TAG = "FrgTb01Bilet";
	
	private BusFrag01BiletBinding biletBinding;
	private BusViewModel sharedBusModel;
	private BiletRVAdapter biletRVAdapter;
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView: frg01");
		biletBinding = DataBindingUtil.inflate(inflater, R.layout.bus_frag01_bilet, container, false);
		
		biletRVAdapter = new BiletRVAdapter();
		biletBinding.recyclerViewBilete.setAdapter(biletRVAdapter);
		
//		sharedBusModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(BusActivityModel.class);
//		sharedBusModel.getLiveDataBilete().observe(this, list -> biletRVAdapter.setBilete(list));
		return biletBinding.getRoot();
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		sharedBusModel =
				ViewModelProviders.of(getActivity()).get(BusViewModel.class);
		subscribeUI(sharedBusModel);
	}
	
	private void subscribeUI(BusViewModel model) {
		model.getLiveDataBilete().observe(getViewLifecycleOwner(), list -> {
			if (list != null){
				biletBinding.setIsLoading(false);
				biletRVAdapter.setBilete(list);
			}else biletBinding.setIsLoading(true);
			biletBinding.executePendingBindings();
		});
	}
	
	public static FrgTb01Bilet newInstance(int page, String title) {
		FrgTb01Bilet fragment = new FrgTb01Bilet();
		Bundle args = new Bundle();
		args.putInt("page", page);
		args.putString("title", title);
		fragment.setArguments(args);
		return fragment;
	}
}
