package com.faciee.cti.valbastrelu.eticket.ui.bus;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
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
import com.faciee.cti.valbastrelu.eticket.databinding.BusFrag01BiletBinding;
import com.faciee.cti.valbastrelu.eticket.ui.bus.model.BusActivityModel;
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.BiletRVAdapter;
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.VerticalSpaceItemDecoration;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;

/**
 * Created by valbastrelu on 09-Apr-18.
 */

public class FrgTb01Bilet extends Fragment{
	private static final String TAG = "FrgTb01Bilet";
	
	private BusFrag01BiletBinding frag01BiletBinding;
//	private BusActivityModel sharedBusModel;
	private BiletRVAdapter biletRVAdapter;
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView: frg01");
		frag01BiletBinding = DataBindingUtil.inflate(inflater, R.layout.bus_frag01_bilet, container, false);
		
		biletRVAdapter = new BiletRVAdapter();
		frag01BiletBinding.recyclerViewBilete.setAdapter(biletRVAdapter);
		
//		sharedBusModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(BusActivityModel.class);
//		sharedBusModel.getLiveDataBilete().observe(this, list -> biletRVAdapter.setBilete(list));
		return frag01BiletBinding.getRoot();
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		final BusActivityModel model =
				ViewModelProviders.of(getActivity()).get(BusActivityModel.class);
		subscribeUI(model);
	}
	
	private void subscribeUI(BusActivityModel model) {
		model.getLiveDataBilete().observe(this, list -> {
			if (list != null){
				frag01BiletBinding.setIsLoading(false);
				biletRVAdapter.setBilete(list);
			}else frag01BiletBinding.setIsLoading(true);
			frag01BiletBinding.executePendingBindings();
		});
	}
}
