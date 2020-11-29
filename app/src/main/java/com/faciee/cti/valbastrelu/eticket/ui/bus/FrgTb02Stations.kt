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
		sharedBusModel = ViewModelProvider(requireActivity(), BusViewModel.getFactory(ETicketApp.currentETicketApp)).get(BusViewModel::class.java)
		arguments?.let { bundle ->
			bundle.getInt(ARG_ROUTE_NUMBER, 0).let {
				sharedBusModel.getLiveDataStatii().observe(viewLifecycleOwner, Observer {
//					buildStepViewStatii(it)
				})
			}
		}
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