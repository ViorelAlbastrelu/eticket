package com.faciee.cti.valbastrelu.eticket.ui.bus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyachi.stepview.VerticalStepView;
import com.faciee.cti.valbastrelu.eticket.R;

import java.util.ArrayList;
import java.util.List;

public class FrgTb02TraseuStatii extends Fragment{
	
	VerticalStepView mSetpview0;
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.stepper_traseu, container, false);
		view.setBackgroundColor(getResources().getColor(R.color.colorPrimaryLight));
		mSetpview0 = view.findViewById(R.id.verticalStepView);
		initVerticalStepView(view);
		return view;
	}
	
	private void initVerticalStepView(View view){
		
		List<String> list0 = new ArrayList<>();
		list0.add("Micro 19-Cinema Dacia");
		list0.add("Otelarilor");
		list0.add("Bloc D19");
		list0.add("Sala Sporturilor");
		list0.add("Flora");
		list0.add("Stadionul Otelul");
		list0.add("Ghe. Doja");
		list0.add("Piata Energiei T");
		list0.add("Liceul 9");
		list0.add("Piata Energiei R");
		list0.add("ICFrimu");
		list0.add("George Cosbuc");
		list0.add("Posta Veche");
		list0.add("Baia Comunala");
		list0.add("Piata Centrala");
		mSetpview0.setStepsViewIndicatorComplectingPosition(0)//设置完成的步数
				.reverseDraw(false)//default is true
				.setStepViewTexts(list0)//总步骤
				.setLinePaddingProportion(0.40f)//设置indicator线与线间距的比例系数
				.setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(getActivity(), android.R.color.white))//设置StepsViewIndicator完成线的颜色
				.setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(getActivity(), R.color.uncompleted_text_color))//设置StepsViewIndicator未完成线的颜色
				.setStepViewComplectedTextColor(ContextCompat.getColor(getActivity(), android.R.color.white))//设置StepsView text完成线的颜色
				.setStepViewUnComplectedTextColor(ContextCompat.getColor(getActivity(), R.color.uncompleted_text_color))//设置StepsView text未完成线的颜色
				.setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(getActivity(), R.drawable.complted))//设置StepsViewIndicator CompleteIcon
				.setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(getActivity(), R.drawable.default_icon))//设置StepsViewIndicator DefaultIcon
				.setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(getActivity(), R.drawable.attention));//设置StepsViewIndicator AttentionIcon
	}
}
