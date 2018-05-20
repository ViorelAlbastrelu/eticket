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
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.BiletRVAdapter;
import com.faciee.cti.valbastrelu.eticket.ui.model.Bilet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valbastrelu on 09-Apr-18.
 */

public class FrgTb01Bilet extends AbstractBusActivityFragment implements FragmentWithListI{
	private static final String TAG = "FrgTb01Bilet";
	private BusPresenter.FrgTb01Presenter frgTb01Presenter;
	RecyclerView recyclerView;
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.bus_frag01_bilet, container, false);
		recyclerView = view.findViewById(R.id.recyclerViewBilete);
		//REFACTOR !
		frgTb01Presenter = getBusPresenter().new FrgTb01Presenter(this);
		// self.buildViewLayout()
		// presenter.refreshData();
//		frgTb01Presenter.populateRecyclerView();
		Log.d(TAG, "onCreateView: frg01");
		return view;
	}
	
	@Override
	public void buildRecyclerView(ArrayList list) {
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		BiletRVAdapter biletRVAdapter = new BiletRVAdapter(list);
		recyclerView.addItemDecoration(new BiletRVAdapter.VerticalSpaceItemDecoration(24));
		recyclerView.setAdapter(biletRVAdapter);
	}
	
	@Override
	public void showDataInList(List list) {
	
	}
}
