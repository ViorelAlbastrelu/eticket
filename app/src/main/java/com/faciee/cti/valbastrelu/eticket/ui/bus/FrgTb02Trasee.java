package com.faciee.cti.valbastrelu.eticket.ui.bus;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProviders;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.databinding.BusFrag021TraseuBinding;
import com.faciee.cti.valbastrelu.eticket.ui.bus.model.BusViewModel;
import com.faciee.cti.valbastrelu.eticket.room.entities.Route;
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.TraseuRVAdapter;

/**
 * Created by valbastrelu on 09-Apr-18.
 */

public class FrgTb02Trasee extends Fragment{
	private static final String TAG = "FrgTb02Trasee";
	
	BusFrag021TraseuBinding traseuBinding;
	TraseuRVAdapter adapter;
	BusViewModel sharedBusModel;
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		traseuBinding = DataBindingUtil.inflate(inflater, R.layout.bus_frag02_1_traseu, container, false);
		Log.d(TAG, "onCreateView: started.");
		//Creating recyclerView and adapter
		adapter = new TraseuRVAdapter(traseuClickCallback);
		traseuBinding.listaTraseeBus.setAdapter(adapter);
		
		//TODO implement filter
		return traseuBinding.getRoot();
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		BusViewModel sharedModel = ViewModelProviders.of(getActivity()).get(BusViewModel.class);
		subscribeUI(sharedModel);
	}
	
	private void subscribeUI(BusViewModel sharedModel) {
		sharedModel.getLiveDataTrasee().observe(getViewLifecycleOwner(), trasee -> {
			if (trasee != null){
				traseuBinding.setIsLoading(false);
				adapter.setTrasee(trasee);
			}else traseuBinding.setIsLoading(true);
		});
	}
	
	public static FrgTb02Trasee newInstance(int page, String title) {
		FrgTb02Trasee fragment = new FrgTb02Trasee();
		Bundle args = new Bundle();
		args.putInt("page", page);
		args.putString("title", title);
		fragment.setArguments(args);
		return fragment;
	}
	
	private final TraseuClickCallback traseuClickCallback = new TraseuClickCallback(){
		@Override
		public void onClick(Route route) {
			if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)){
				Toast.makeText(getContext(), "Traseu clicked : " + route.getNumber(), Toast.LENGTH_SHORT).show();
				FrgTb02Statii frgTb02Statii = FrgTb02Statii.statiiPentruTraseu(route.getNumber());
				frgTb02Statii.show(getChildFragmentManager(), FrgTb02Statii.getTAG());
			}
		}
	};
}
