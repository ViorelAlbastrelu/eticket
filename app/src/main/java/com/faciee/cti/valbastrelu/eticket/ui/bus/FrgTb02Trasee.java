package com.faciee.cti.valbastrelu.eticket.ui.bus;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.databinding.BusFrag021TraseuBinding;
import com.faciee.cti.valbastrelu.eticket.ui.bus.model.BusActivityModel;
import com.faciee.cti.valbastrelu.eticket.ui.bus.model.Traseu;
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.TraseuRVAdapter;

/**
 * Created by valbastrelu on 09-Apr-18.
 */

public class FrgTb02Trasee extends Fragment{
	private static final String TAG = "FrgTb02Trasee";
	
	BusFrag021TraseuBinding traseuBinding;
	TraseuRVAdapter adapter;
	BusActivityModel sharedBusModel;
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		traseuBinding = DataBindingUtil.inflate(inflater, R.layout.bus_frag02_1_traseu, container, false);
		Log.d(TAG, "onCreateView: started.");
		//Creating recyclerView and adapter
		adapter = new TraseuRVAdapter(traseuClickCallback);
		traseuBinding.listaTraseeBus.setAdapter(adapter);
		
		//TODO  Pass listener to recyclerView onClick infoBtn to open FrgTb02TraseuStep based on selected.
		//TODO implement filter
		return traseuBinding.getRoot();
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		BusActivityModel sharedModel = ViewModelProviders.of(getActivity()).get(BusActivityModel.class);
		subscribeUI(sharedModel);
	}
	
	private void subscribeUI(BusActivityModel sharedModel) {
		sharedModel.getLiveDataTrasee().observe(this, trasee -> {
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
		public void onClick(Traseu traseu) {
			if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)){
				Toast.makeText(getContext(), "Traseu clicked : " + traseu.getNrTraseu(), Toast.LENGTH_SHORT).show();
				FrgTb02Statii frgTb02Statii = FrgTb02Statii.statiiPentruTraseu(traseu.getNrTraseu());
				frgTb02Statii.show(getFragmentManager(), FrgTb02Statii.getTAG());
			}
		}
	};
}
