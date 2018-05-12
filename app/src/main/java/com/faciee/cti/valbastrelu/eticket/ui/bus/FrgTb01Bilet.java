package com.faciee.cti.valbastrelu.eticket.ui.bus;

import android.graphics.Rect;
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
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.BiletRVAdapter;
import com.faciee.cti.valbastrelu.eticket.ui.model.Bilet;

import java.util.ArrayList;

/**
 * Created by valbastrelu on 09-Apr-18.
 */

public class FrgTb01Bilet extends Fragment {
	private static final String TAG = "FrgTb01Bilet";
	private ArrayList<Bilet> listaBilete = new ArrayList<>();
	RecyclerView recyclerView;
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.bus_frag01_bilet, container, false);
		recyclerView = view.findViewById(R.id.recyclerViewBilete);
		initList();
		initRecyclerView(view);
		Log.d(TAG, "onCreateView: frg01");
		return view;
	}
	
	private void initList(){
		listaBilete.add(new Bilet(102, true, 2,2));
		listaBilete.add(new Bilet(7, false, 2,2));
		listaBilete.add(new Bilet(44, false, 2,2));
	}
	
	private void initRecyclerView(View view){
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		BiletRVAdapter biletRVAdapter = new BiletRVAdapter(listaBilete);
		recyclerView.addItemDecoration(new BiletRVAdapter.VerticalSpaceItemDecoration(24));
		recyclerView.setAdapter(biletRVAdapter);
	}
}
