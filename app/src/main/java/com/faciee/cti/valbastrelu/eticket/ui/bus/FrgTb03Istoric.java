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
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.IstoricRVAdapter;

import java.util.ArrayList;

/**
 * Created by valbastrelu on 09-Apr-18.
 */

public class FrgTb03Istoric extends Fragment {
	private static final String TAG = "FrgTb03Istoric";
	
	ArrayList<String> mData = new ArrayList<>();
	ArrayList<String> mTipBilete = new ArrayList<>();
	ArrayList<String> mSuma = new ArrayList<>();
	//TODO filtre by date
	//TODO filtre by transport number
	//TODO filtre by type
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.bus_frag03_istoric, container,false);
		Log.d(TAG, "onCreateView: started.");
		initListe();
		initRecyclerView(view);
		return view;
	}
	
	private void initListe(){
		mData.add("12 IAN"); mTipBilete.add("Bilet autobuz (#106)"); mSuma.add("-1.00 RON");
		mData.add("13 IAN"); mTipBilete.add("Bilet autobuz (#6)"); mSuma.add("-1.00 RON");
		mData.add("15 IAN"); mTipBilete.add("Bilet autobuz (#10)"); mSuma.add("-1.00 RON");
		mData.add("18 IAN"); mTipBilete.add("Bilet autobuz (#106)"); mSuma.add("-1.00 RON");
		mData.add("19 IAN"); mTipBilete.add("Bilet autobuz (#6)"); mSuma.add("-1.00 RON");
		mData.add("21 IAN"); mTipBilete.add("Bilet autobuz (#106)"); mSuma.add("-1.00 RON");
		mData.add("22 IAN"); mTipBilete.add("Bilet autobuz (#10)"); mSuma.add("-1.00 RON");
		mData.add("24 IAN"); mTipBilete.add("Bilet autobuz (#106)"); mSuma.add("-1.00 RON");
		mData.add("24 IAN"); mTipBilete.add("Bilet autobuz (#6)"); mSuma.add("-1.00 RON");
		mData.add("26 IAN"); mTipBilete.add("Bilet autobuz (#10)"); mSuma.add("-1.00 RON");
	
	}
	
	private void initRecyclerView(View view){
		Log.d(TAG, "initRecyclerView: initializing...");
		RecyclerView recyclerView = view.findViewById(R.id.listaIstoric);
		IstoricRVAdapter adapter = new IstoricRVAdapter(mData, mTipBilete, mSuma);
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
	}
}
