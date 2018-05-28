package com.faciee.cti.valbastrelu.eticket.ui.bus;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.ui.bus.model.BusActivityModel;
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.TraseuRVAdapter;

import java.util.List;

/**
 * Created by valbastrelu on 09-Apr-18.
 */

public class FrgTb02TraseuMain extends Fragment{
	private static final String TAG = "FrgTb02TraseuMain";
	
	BusActivityModel sharedBusModel;
	RecyclerView recyclerView;
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.bus_frag02_traseu, container, false);
		Log.d(TAG, "onCreateView: started.");
		//Creating recyclerView and adapter
		TraseuRVAdapter adapter = new TraseuRVAdapter(getContext());
		recyclerView = view.findViewById(R.id.listaTraseeBus);
		buildRecyclerView(adapter);
		
		sharedBusModel = ViewModelProviders.of(getActivity()).get(BusActivityModel.class);
		sharedBusModel.getLiveDataTrasee().observe(this, trasee -> {
			adapter.setTrasee(trasee);
		});
		//TODO  Pass listener to recyclerView onClick infoBtn to open FrgTb02TraseuStep based on selected.
		//TODO implement filter
		return view;
	}
	
	public void buildRecyclerView(TraseuRVAdapter adapter) {
		Log.d(TAG, "initRecyclerView: initializing...");
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		recyclerView.setAdapter(adapter);
	}
}
