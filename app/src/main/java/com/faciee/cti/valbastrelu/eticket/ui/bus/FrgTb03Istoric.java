package com.faciee.cti.valbastrelu.eticket.ui.bus;

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
import com.faciee.cti.valbastrelu.eticket.ui.bus.i.FragmentViewI;
import com.faciee.cti.valbastrelu.eticket.ui.bus.presenter.BusPresenter;
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.IstoricRVAdapter;

import java.util.ArrayList;

/**
 * Created by valbastrelu on 09-Apr-18.
 */

public class FrgTb03Istoric extends Fragment implements FragmentViewI{
	private static final String TAG = "FrgTb03Istoric";
	private BusPresenter.FrgTb03Presenter frgTb03Presenter;
	
	//TODO filtre by date
	//TODO filtre by transport number
	//TODO filtre by type
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.bus_frag03_istoric, container,false);
		frgTb03Presenter = new BusPresenter.FrgTb03Presenter(this);
		frgTb03Presenter.populateRecyclerView(view);
		Log.d(TAG, "onCreateView: started.");
		return view;
	}
	
	@Override
	public void buildRecyclerView(View view, ArrayList list) {
		Log.d(TAG, "initRecyclerView: initializing...");
		RecyclerView recyclerView = view.findViewById(R.id.listaIstoric);
		IstoricRVAdapter adapter = new IstoricRVAdapter(getContext(), list);
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
	}
}
