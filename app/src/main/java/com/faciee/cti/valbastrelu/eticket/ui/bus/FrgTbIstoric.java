package com.faciee.cti.valbastrelu.eticket.ui.bus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.faciee.cti.valbastrelu.eticket.R;

/**
 * Created by valbastrelu on 09-Apr-18.
 */

public class FrgTbIstoric extends Fragment {
	private static final String TAG = "FrgTbIstoric";
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.bus_fragment_istoric, container,false);
		return view;
	}
}
