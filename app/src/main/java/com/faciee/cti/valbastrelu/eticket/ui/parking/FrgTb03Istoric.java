package com.faciee.cti.valbastrelu.eticket.ui.parking;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.IstoricRVAdapter;
import com.faciee.cti.valbastrelu.eticket.ui.parking.model.ParkingActivityModel;

public class FrgTb03Istoric extends Fragment {
	private static final String TAG = "FrgTb03Istoric";
	
	private RecyclerView listaIstoric;
	private ParkingActivityModel sharedParkingModel;
	public FrgTb03Istoric() {
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView: Parking tab03 started");
		View view = inflater.inflate(R.layout.parking_frg_tb03_istoric, container, false);
		IstoricRVAdapter adapter = new IstoricRVAdapter(getContext());
		listaIstoric = view.findViewById(R.id.listaIstoric);
		buildRecyclerView(adapter);
		sharedParkingModel = ViewModelProviders.of(this).get(ParkingActivityModel.class);
		sharedParkingModel.getLiveDataTranzactii().observe(this, tranzactii -> adapter.setIstoric(tranzactii));
		
		return view;
	}
	
	public void buildRecyclerView(IstoricRVAdapter adapter) {
		Log.d(TAG, "initRecyclerView: initializing...");
		listaIstoric.setHasFixedSize(true);
		listaIstoric.setAdapter(adapter);
		listaIstoric.setLayoutManager(new LinearLayoutManager(getContext()));
	}
	
}
