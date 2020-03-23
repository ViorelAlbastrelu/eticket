package com.faciee.cti.valbastrelu.eticket.ui.parking;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.faciee.cti.valbastrelu.eticket.databinding.ParkingFrgTb03IstoricBinding;
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.IstoricRVAdapter;
import com.faciee.cti.valbastrelu.eticket.ui.parking.model.ParkingViewModel;

public class FrgTb03Istoric extends Fragment {
	private static final String TAG = "FrgTb03Istoric";
	private ParkingFrgTb03IstoricBinding istoricBinding;
	private RecyclerView listaIstoric;
	private ParkingViewModel sharedParkingModel;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView: Parking tab03 started");
		istoricBinding = ParkingFrgTb03IstoricBinding.inflate(getLayoutInflater(), container, false);
		IstoricRVAdapter adapter = new IstoricRVAdapter(getContext());
		buildRecyclerView(adapter);
		sharedParkingModel = new ViewModelProvider(this).get(ParkingViewModel.class);
		sharedParkingModel.getLiveDataTranzactii().observe(getViewLifecycleOwner(), adapter::setIstoric);

		return istoricBinding.getRoot();
	}

	public void buildRecyclerView(IstoricRVAdapter adapter) {
		Log.d(TAG, "initRecyclerView: initializing...");
		listaIstoric = istoricBinding.listaIstoric;
		listaIstoric.setHasFixedSize(true);
		listaIstoric.setAdapter(adapter);
	}
	
}
