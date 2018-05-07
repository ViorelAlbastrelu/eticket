package com.faciee.cti.valbastrelu.eticket.ui.bus;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.ui.model.Bilet;

import java.util.ArrayList;

/**
 * Created by valbastrelu on 09-Apr-18.
 */

public class FrgTb01Bilet extends Fragment {
	private static final String TAG = "FrgTb01Bilet";
	private ArrayList<Bilet> listaBilete = new ArrayList<>();
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.bus_frag01_bilet, container, false);
		Log.d(TAG, "onCreateView: frg01");
		return view;
	}
}
