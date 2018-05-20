package com.faciee.cti.valbastrelu.eticket.ui.bus;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baoyachi.stepview.VerticalStepView;
import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.ui.bus.i.FragmentViewI;
import com.faciee.cti.valbastrelu.eticket.ui.bus.i.FragmentWithListI;
import com.faciee.cti.valbastrelu.eticket.ui.bus.model.BusActivityModel;
import com.faciee.cti.valbastrelu.eticket.ui.bus.presenter.BusPresenter;
import com.faciee.cti.valbastrelu.eticket.ui.model.Statie;

import java.util.ArrayList;
import java.util.List;

public class FrgTb02TraseuStatii extends AbstractBusActivityFragment implements FragmentWithListI {
	private BusPresenter.FrgTb02StatiiPresenter statiiPresenter;
	private BusActivityModel sharedViewModel;
	VerticalStepView mSetpview0;
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.stepper_traseu, container, false);
		view.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
		sharedViewModel = ViewModelProviders.of(getActivity()).get(BusActivityModel.class);
		sharedViewModel.getLiveDataStatii(0).observe(this, statii -> {
			
		});
		mSetpview0 = view.findViewById(R.id.verticalStepView);
		statiiPresenter = getBusActivity().busPresenter.new FrgTb02StatiiPresenter(this);
		statiiPresenter.populateRecyclerView();
		return view;
	}
	
	@Override
	public void buildRecyclerView(ArrayList list) {
		
		mSetpview0.setStepsViewIndicatorComplectingPosition(-1)
				.reverseDraw(false)
				.setStepViewTexts(list)
				.setLinePaddingProportion(0.40f)//indicator
				.setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(getContext(), android.R.color.white))//StepsViewIndicator
				.setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(getContext(), R.color.uncompleted_text_color))//StepsViewIndicator
				.setStepViewComplectedTextColor(ContextCompat.getColor(getContext(), android.R.color.white))//StepsView text
				.setStepViewUnComplectedTextColor(ContextCompat.getColor(getContext(), R.color.uncompleted_text_color))//StepsView text
				.setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(getContext(), R.drawable.complted))//StepsViewIndicator CompleteIcon
				.setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(getContext(), R.drawable.default_icon))//StepsViewIndicator DefaultIcon
				.setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(getContext(), R.drawable.attention));//StepsViewIndicator AttentionIcon
	}
	
	@Override
	public void showDataInList(List list) {
	
	}
}
