package com.faciee.cti.valbastrelu.eticket.ui.bus;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
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
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.IstoricRVAdapter;

/**
 * Created by valbastrelu on 09-Apr-18.
 */

public class FrgTb03Istoric extends Fragment{
	private static final String TAG = "FrgTb03Istoric";
	
	private RecyclerView listaIstoric;
	private BusActivityModel sharedBusModel;
	//TODO filter by date
	//TODO filter by transport number
	//TODO filter by type
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.bus_frag03_istoric, container,false);
		IstoricRVAdapter adapter = new IstoricRVAdapter(getContext());
		listaIstoric = view.findViewById(R.id.listaIstoric);
		buildRecyclerView(adapter);
		sharedBusModel = ViewModelProviders.of(getActivity()).get(BusActivityModel.class);
		sharedBusModel.getLiveDataTranzactii().observe(this, tranzactii -> {
			adapter.setIstoric(tranzactii);
		});
		Log.d(TAG, "onCreateView: started.");
		return view;
	}
	
	public void buildRecyclerView(IstoricRVAdapter adapter) {
		Log.d(TAG, "initRecyclerView: initializing...");
		listaIstoric.setHasFixedSize(true);
		listaIstoric.setAdapter(adapter);
		listaIstoric.setLayoutManager(new LinearLayoutManager(getContext()));
	}
}
