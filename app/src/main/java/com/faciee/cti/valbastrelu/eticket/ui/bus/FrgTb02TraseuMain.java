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
import com.faciee.cti.valbastrelu.eticket.ui.bus.i.FragmentWithListI;
import com.faciee.cti.valbastrelu.eticket.ui.bus.presenter.BusPresenter;
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.TraseuRVAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valbastrelu on 09-Apr-18.
 */

public class FrgTb02TraseuMain extends AbstractBusActivityFragment implements FragmentWithListI{
	private static final String TAG = "FrgTb02TraseuMain";
	private BusPresenter.FrgTb02MainPresenter frgTb02Presenter;
	
	RecyclerView recyclerView;
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.bus_frag02_traseu, container, false);
		recyclerView = view.findViewById(R.id.listaTraseeBus);
		frgTb02Presenter = getBusPresenter().new FrgTb02MainPresenter(this);
		frgTb02Presenter.populateRecyclerView();
		//TODO  Pass listener to recyclerView onClick infoBtn to open FrgTb02TraseuStep based on selected.
		Log.d(TAG, "onCreateView: started.");
		return view;
	}
	
	private void initList(){
		Log.d(TAG, "initList: prepare lists.");
		//TODO implement filter
	}
	
	@Override
	public void buildRecyclerView(ArrayList list) {
		Log.d(TAG, "initRecyclerView: initializing...");
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		TraseuRVAdapter adapter = new TraseuRVAdapter(getContext(), list);
		recyclerView.setAdapter(adapter);
	}
	
	@Override
	public void showDataInList(List list) {
	
	}
}
