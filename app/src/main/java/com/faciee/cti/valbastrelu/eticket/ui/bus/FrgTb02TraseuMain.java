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
import com.faciee.cti.valbastrelu.eticket.ui.bus.presenter.FrgTb02Presenter;
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.TraseuRVAdapter;

import java.util.ArrayList;

/**
 * Created by valbastrelu on 09-Apr-18.
 */

public class FrgTb02TraseuMain extends Fragment implements FragmentViewI {
	private static final String TAG = "FrgTb02TraseuMain";
	private FrgTb02Presenter frgTb02Presenter;
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.bus_frag02_traseu, container, false);
		frgTb02Presenter = new FrgTb02Presenter(this);
		frgTb02Presenter.populateRecylerView(view);
		//TODO  Pass listener to recyclerView onClick infoBtn to open FrgTb02TraseuStep based on selected.
		Log.d(TAG, "onCreateView: started.");
		return view;
	}
	
	private void initList(){
		Log.d(TAG, "initList: prepare lists.");
		//TODO add type to traseu (posibly Enum class with icons)
		//TODO implement filter
	}
	
	@Override
	public void buildRecyclerView(View view, ArrayList list) {
		Log.d(TAG, "initRecyclerView: initializing...");
		RecyclerView recyclerView = view.findViewById(R.id.listaTraseeBus);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		TraseuRVAdapter adapter = new TraseuRVAdapter(getContext(), list);
		recyclerView.setAdapter(adapter);
	}
}
