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
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.VerticalSpaceItemDecoration;

import java.util.List;
import java.util.Objects;

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
		Log.d(TAG, "onCreateView: frg01");
		
		recyclerView = view.findViewById(R.id.recyclerViewBilete);
		BiletRVAdapter biletRVAdapter = new BiletRVAdapter();
		buildRecyclerView(biletRVAdapter);
		
		sharedBusModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(BusActivityModel.class);
		sharedBusModel.getLiveDataBilete().observe(this, list -> biletRVAdapter.setBilete(list));
		return view;
	}
	
	public void buildRecyclerView(BiletRVAdapter biletRVAdapter) {
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//		recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(24));
		recyclerView.setAdapter(biletRVAdapter);
	}
}
