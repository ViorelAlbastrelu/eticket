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
import com.faciee.cti.valbastrelu.eticket.util.model.Tranzactie;

import java.util.List;

/**
 * Created by valbastrelu on 09-Apr-18.
 */

public class FrgTb03Istoric extends Fragment{
	private static final String TAG = "FrgTb03Istoric";
	
	private BusActivityModel sharedBusModel;
	//TODO filtre by date
	//TODO filtre by transport number
	//TODO filtre by type
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.bus_frag03_istoric, container,false);
		sharedBusModel = ViewModelProviders.of(getActivity()).get(BusActivityModel.class);
		sharedBusModel.getLiveDataTranzactii().observe(this, tranzactii -> {
			buildRecyclerView(tranzactii);
		});
		Log.d(TAG, "onCreateView: started.");
		return view;
	}
	
	public void buildRecyclerView(List<Tranzactie> list) {
		Log.d(TAG, "initRecyclerView: initializing...");
		RecyclerView recyclerView = getView().findViewById(R.id.listaIstoric);
		IstoricRVAdapter adapter = new IstoricRVAdapter(getContext(), list);
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
	}
}
