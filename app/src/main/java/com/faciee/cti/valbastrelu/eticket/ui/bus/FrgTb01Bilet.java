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
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.BiletRVAdapter;

import java.util.List;

/**
 * Created by valbastrelu on 09-Apr-18.
 */

public class FrgTb01Bilet extends Fragment{
	private static final String TAG = "FrgTb01Bilet";
	
	private BusActivityModel sharedBusModel;
	private RecyclerView recyclerView;
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.bus_frag01_bilet, container, false);
		sharedBusModel = ViewModelProviders.of(getActivity()).get(BusActivityModel.class);
		sharedBusModel.getLiveDataBilete().observe(this, this::buildRecyclerView);
		recyclerView = view.findViewById(R.id.recyclerViewBilete);
		Log.d(TAG, "onCreateView: frg01");
		return view;
	}
	
	public void buildRecyclerView(List list) {
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		BiletRVAdapter biletRVAdapter = new BiletRVAdapter(list);
		recyclerView.addItemDecoration(new BiletRVAdapter.VerticalSpaceItemDecoration(24));
		recyclerView.setAdapter(biletRVAdapter);
	}
}
