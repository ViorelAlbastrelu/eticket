package com.faciee.cti.valbastrelu.eticket.ui.bus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.faciee.cti.valbastrelu.eticket.R
import com.faciee.cti.valbastrelu.eticket.base.ETicketApp
import com.faciee.cti.valbastrelu.eticket.databinding.BusFrag02StationsBinding
import com.faciee.cti.valbastrelu.eticket.ui.bus.vm.BusViewModel

class FrgTb02Stations : DialogFragment() {
	private lateinit var stationsBinding: BusFrag02StationsBinding
	private lateinit var sharedBusModel: BusViewModel

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		stationsBinding = DataBindingUtil.inflate(inflater, R.layout.bus_frag02_stations, container, false)
		return stationsBinding.root
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		sharedBusModel = ViewModelProvider(activity !!, BusViewModel.getFactory(ETicketApp.currentETicketApp)).get(BusViewModel::class.java)
		arguments?.let { bundle ->
			bundle.getInt(ARG_ROUTE_NUMBER, 0).let {
				sharedBusModel.getLiveDataStatii(it).observe(viewLifecycleOwner, Observer {
					buildStepViewStatii(it)
				})
			}
		}
	}

	fun buildStepViewStatii(list: List<String?>?) { //		statiiBinding.verticalStepView
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

	companion object {
		const val TAG = "FrgTb02Stations"
		private const val ARG_ROUTE_NUMBER = "arg_route_number"

		fun newInstance(routeNumber: Int) = FrgTb02Stations().apply {
			arguments = Bundle().apply {
				putInt(ARG_ROUTE_NUMBER, routeNumber)
			}
		}

	}
}