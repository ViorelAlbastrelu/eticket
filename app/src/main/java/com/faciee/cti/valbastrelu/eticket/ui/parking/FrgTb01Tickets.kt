package com.faciee.cti.valbastrelu.eticket.ui.parking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.faciee.cti.valbastrelu.eticket.R
import com.faciee.cti.valbastrelu.eticket.base.SharingFragment
import com.faciee.cti.valbastrelu.eticket.databinding.ParkingFrag01TicketsBinding
import com.faciee.cti.valbastrelu.eticket.room.entities.TicketParking
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.ParkingTicketsAdapter
import com.faciee.cti.valbastrelu.eticket.ui.parking.model.ParkingViewModel

class FrgTb01Tickets : SharingFragment<ParkingViewModel, ParkingViewModel>() {
	private lateinit var binding: ParkingFrag01TicketsBinding
	private lateinit var ticketsAdapter: ParkingTicketsAdapter

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
							  savedInstanceState: Bundle?): View? {
		binding = DataBindingUtil.inflate(inflater, R.layout.parking_frag01_tickets, container, false)
		ticketsAdapter = ParkingTicketsAdapter()
		binding.ticketsRecycler.adapter = ticketsAdapter
		return binding.root
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		initSharedViewModel(ParkingViewModel::class.java, ParkingViewModel.getFactory(eTicketApp))
		subscribeUI()
	}

	private fun subscribeUI() {
		sharedViewModel.ticketsLiveData.observe(viewLifecycleOwner, Observer {
			binding.isLoading = false
			ticketsAdapter.updateTickets(it)
		})
	}

//	val recycleViewCallback = UpdateRecyclerViewCallback { binding.recyclerViewBileteP.scrollTo(0, 0) }

	companion object {
		private const val TAG = "FrgTb01Tickets"

		fun newInstance() = FrgTb01Tickets()
	}
}