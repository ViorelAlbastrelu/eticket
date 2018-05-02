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
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.TraseuRecyclerViewAdapter;

import java.util.ArrayList;

/**
 * Created by valbastrelu on 09-Apr-18.
 */

public class FrgTb02TraseuMain extends Fragment {
	private static final String TAG = "FrgTb02TraseuMain";
	
	//lists
	private ArrayList<String> mTimeStamps = new ArrayList<>();
	private ArrayList<String> mTrasee = new ArrayList<>();
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.bus_frag02_traseu, container, false);
		initList();
		initRecyclerView(view);
		//TODO  Pass listener to recyclerView onClick infoBtn to open FrgTb02TraseuStep based on selected.
		Log.d(TAG, "onCreateView: started.");
		return view;
	}
	
	private void initList(){
		Log.d(TAG, "initList: prepare lists.");
		
		mTimeStamps.add("10:10");
		mTrasee.add("7");
		mTimeStamps.add("10:25");
		mTrasee.add("9");
		mTimeStamps.add("10:48");
		mTrasee.add("11");
		mTimeStamps.add("11:00");
		mTrasee.add("7");
		mTimeStamps.add("11:12");
		mTrasee.add("9");
		mTimeStamps.add("11:34");
		mTrasee.add("11");
		mTimeStamps.add("11:48");
		mTrasee.add("9");
		mTimeStamps.add("12:02");
		mTrasee.add("11");
		mTimeStamps.add("12:02");
		mTrasee.add("11");
		mTimeStamps.add("12:02");
		mTrasee.add("11");
		mTimeStamps.add("12:02");
		mTrasee.add("11");
		mTimeStamps.add("12:02");
		mTrasee.add("11");
	}
	
	private void initRecyclerView(View view){
		Log.d(TAG, "initRecyclerView: initializing...");
		RecyclerView recyclerView = view.findViewById(R.id.listaTraseeBus);
		TraseuRecyclerViewAdapter adapter = new TraseuRecyclerViewAdapter(getContext(), mTimeStamps, mTrasee);
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
	}
	
}
