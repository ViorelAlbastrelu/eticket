package com.faciee.cti.valbastrelu.eticket.ui.parking;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.faciee.cti.valbastrelu.eticket.databinding.ParkingFrgTb02ParcariBinding;

public class FrgTb02Parcari extends Fragment {
	private static final String TAG = "FrgTb02Parcari";
	private ParkingFrgTb02ParcariBinding parcariBinding;
	private View descriptionLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		parcariBinding = ParkingFrgTb02ParcariBinding.inflate(getLayoutInflater(), container, false);
		descriptionLayout = parcariBinding.parkingDescription;
		String description = "Strada: Domnească \nLocuri: 3/25 \nCost taxă: 1.5 RON/h";
		parcariBinding.title.setText("POI: Parcare Nespălata");
		parcariBinding.content.setText(description);
		parcariBinding.pinIcon.setOnClickListener(v -> descriptionLayout.setVisibility(descriptionLayout.getVisibility() == View.VISIBLE ?
		View.INVISIBLE : View.VISIBLE));
		return parcariBinding.getRoot();
	}
}
