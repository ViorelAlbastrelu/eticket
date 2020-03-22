package com.faciee.cti.valbastrelu.eticket.ui.bus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.databinding.BusFrag022StatiiBinding;
import com.faciee.cti.valbastrelu.eticket.ui.bus.model.BusViewModel;

import java.util.List;

public class FrgTb02Statii extends DialogFragment {
	private static final String TAG = "FrgTb02Statii";
	private static final String NR_TRASEU = "nr_traseu";
	
	private BusFrag022StatiiBinding statiiBinding;
	private BusViewModel sharedBusModel;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		statiiBinding = DataBindingUtil
				.inflate(inflater, R.layout.bus_frag02_2_statii, container, false);

		sharedBusModel = ViewModelProviders.of(getActivity()).get(BusViewModel.class);
		sharedBusModel.getLiveDataStatii(getArguments() != null ? getArguments().getInt(NR_TRASEU) : 0).observe(this, statii -> {
			buildStepViewStatii(statii);
		});
		return statiiBinding.getRoot();
	}
	
	public void buildStepViewStatii(List<String> list) {
//		statiiBinding.verticalStepView
//				.setStepsViewIndicatorComplectingPosition(list.size())
//				.reverseDraw(false)
//				.setStepViewTexts(list)
//				.setLinePaddingProportion(0.40f)//indicator
//				.setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(getContext(), android.R.color.black))//StepsViewIndicator
//				.setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(getContext(), R.color.colorPrimary))//StepsViewIndicator
//				.setStepViewComplectedTextColor(ContextCompat.getColor(getContext(), android.R.color.black))//StepsView text
//				.setStepViewUnComplectedTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary))//StepsView text
//				.setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(getContext(), R.drawable.point))//StepsViewIndicator CompleteIcon
//				.setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(getContext(), R.drawable.point))//StepsViewIndicator DefaultIcon
//				.setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(getContext(), R.drawable.attention));//StepsViewIndicator AttentionIcon
//				.setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(getContext(), R.drawable.complted))//StepsViewIndicator CompleteIcon
	}
	
	public static FrgTb02Statii newInstance(int page, String title) {
		FrgTb02Statii fragment = new FrgTb02Statii();
		Bundle args = new Bundle();
		args.putInt("page", page);
		args.putString("title", title);
		fragment.setArguments(args);
		return fragment;
	}
	
	public static FrgTb02Statii statiiPentruTraseu(int nrTraseu){
		FrgTb02Statii statii = new FrgTb02Statii();
		Bundle args = new Bundle();
		args.putInt(NR_TRASEU, nrTraseu);
		statii.setArguments(args);
		return statii;
	}
	
	public static String getTAG() {
		return TAG;
	}
}
