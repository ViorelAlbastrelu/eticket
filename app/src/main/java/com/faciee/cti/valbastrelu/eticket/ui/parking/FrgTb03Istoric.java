package com.faciee.cti.valbastrelu.eticket.ui.parking;


import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.databinding.ParkingFrgTb03IstoricBinding;
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.IstoricRVAdapter;
import com.faciee.cti.valbastrelu.eticket.ui.parking.model.ParkingActivityViewModel;

public class FrgTb03Istoric extends Fragment {
	private static final String TAG = "FrgTb03Istoric";
	private ParkingFrgTb03IstoricBinding istoricBinding;
	private RecyclerView listaIstoric;
	private ParkingActivityViewModel sharedParkingModel;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView: Parking tab03 started");
		istoricBinding = ParkingFrgTb03IstoricBinding.inflate(getLayoutInflater(), container, false);
		IstoricRVAdapter adapter = new IstoricRVAdapter(getContext());
		listaIstoric = istoricBinding.listaIstoric;
		buildRecyclerView(adapter);
		sharedParkingModel = ViewModelProviders.of(this).get(ParkingActivityViewModel.class);
		sharedParkingModel.getLiveDataTranzactii().observe(getViewLifecycleOwner(), adapter::setIstoric);
		
		return istoricBinding.getRoot();
	}
	
	public void buildRecyclerView(IstoricRVAdapter adapter) {
		Log.d(TAG, "initRecyclerView: initializing...");
		listaIstoric.setHasFixedSize(true);
		listaIstoric.setAdapter(adapter);
		listaIstoric.setLayoutManager(new LinearLayoutManager(getContext()));
	}
	
}
