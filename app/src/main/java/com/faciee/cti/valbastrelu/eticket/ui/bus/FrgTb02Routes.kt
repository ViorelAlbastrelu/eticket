package com.faciee.cti.valbastrelu.eticket.ui.bus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import com.faciee.cti.valbastrelu.eticket.R
import com.faciee.cti.valbastrelu.eticket.base.SharingFragment
import com.faciee.cti.valbastrelu.eticket.databinding.BusFrag02RoutesBinding
import com.faciee.cti.valbastrelu.eticket.room.entities.Route
import com.faciee.cti.valbastrelu.eticket.ui.bus.vm.BusViewModel
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.RoutesAdapter

/**
 * Created by valbastrelu on 09-Apr-18.
 */
class FrgTb02Routes : SharingFragment<BusViewModel, BusViewModel>(), RouteClickListener {
	lateinit var routesBinding: BusFrag02RoutesBinding
	lateinit var routesAdapter: RoutesAdapter

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		routesBinding = DataBindingUtil.inflate(inflater, R.layout.bus_frag02_routes, container, false)
		routesAdapter = RoutesAdapter(this)
		routesBinding.listaTraseeBus.adapter = routesAdapter
		//TODO implement filter
		return routesBinding.root
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		initSharedViewModel(BusViewModel::class.java, BusViewModel.getFactory(eTicketApp))
		subscribeUI()
	}

	override fun onRouteClicked(routeNumber: Int) {
		if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
			Toast.makeText(context, "Route clicked : $routeNumber", Toast.LENGTH_SHORT).show()
			val frgTb02Stations = FrgTb02Stations.newInstance(routeNumber)
			frgTb02Stations.show(childFragmentManager, FrgTb02Stations.TAG)
		}
	}

	private fun subscribeUI() {
		sharedViewModel.liveDataTrasee.observe(viewLifecycleOwner, Observer { trasee: List<Route> ->
			routesBinding.isLoading = false
			routesAdapter.routes = trasee
		})
	}
	companion object {

		private const val TAG = "FrgTb02Routes"
		fun newInstance() = FrgTb02Routes()

	}
}