package com.faciee.cti.valbastrelu.eticket.ui.parking;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.faciee.cti.valbastrelu.eticket.R;

public class FrgTb02Parcari extends Fragment {
	private static final String TAG = "FrgTb02Parcari";
	ImageView pin;
	TextView title;
	TextView content;
	View descriptionLayout;
	public FrgTb02Parcari() {
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.parking_frg_tb02_parcari, container, false);
		descriptionLayout = view.findViewById(R.id.parkingDescription);
		title = view.findViewById(R.id.title);
		content = view.findViewById(R.id.content);
		String description = "Strada: Domnească \nLocuri: 3/25 \nCost taxă: 1.5 RON/h";
		title.setText("POI: Parcare Nespălata");
		content.setText(description);
		pin = view.findViewById(R.id.pinIcon);
		pin.setOnClickListener(v -> descriptionLayout.setVisibility(descriptionLayout.getVisibility() == View.VISIBLE ?
		View.INVISIBLE : View.VISIBLE));
		return view;
	}
}
