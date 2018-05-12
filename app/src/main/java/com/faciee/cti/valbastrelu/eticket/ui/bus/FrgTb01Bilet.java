package com.faciee.cti.valbastrelu.eticket.ui.bus;

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
import com.faciee.cti.valbastrelu.eticket.ui.bus.i.FragmentViewI;
import com.faciee.cti.valbastrelu.eticket.ui.bus.presenter.FrgTb01Presenter;
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.BiletRVAdapter;

import java.util.ArrayList;

/**
 * Created by valbastrelu on 09-Apr-18.
 */

public class FrgTb01Bilet extends Fragment implements FragmentViewI {
	private static final String TAG = "FrgTb01Bilet";
	private FrgTb01Presenter frgTb01Presenter;
	RecyclerView recyclerView;
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.bus_frag01_bilet, container, false);
		recyclerView = view.findViewById(R.id.recyclerViewBilete);
		frgTb01Presenter = new FrgTb01Presenter(this);
		frgTb01Presenter.populateRecylerView(view);
		Log.d(TAG, "onCreateView: frg01");
		return view;
	}
	
	@Override
	public void buildRecyclerView(View view, ArrayList list) {
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		BiletRVAdapter biletRVAdapter = new BiletRVAdapter(list);
		recyclerView.addItemDecoration(new BiletRVAdapter.VerticalSpaceItemDecoration(24));
		recyclerView.setAdapter(biletRVAdapter);
	}
}
