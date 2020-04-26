package com.faciee.cti.valbastrelu.eticket.ui.bus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import com.faciee.cti.valbastrelu.eticket.R
import com.faciee.cti.valbastrelu.eticket.base.SharingFragment
import com.faciee.cti.valbastrelu.eticket.databinding.BusFrag01TicketBinding
import com.faciee.cti.valbastrelu.eticket.room.entities.Ticket
import com.faciee.cti.valbastrelu.eticket.ui.bus.vm.BusViewModel
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.TicketsAdapter
import com.faciee.cti.valbastrelu.eticket.util.DummyData

/**
 * Created by valbastrelu on 09-Apr-18.
 */
class FrgTb01Ticket : SharingFragment<AndroidViewModel, BusViewModel>() {
	private lateinit var biletBinding: BusFrag01TicketBinding
	private lateinit var ticketsAdapter: TicketsAdapter

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		biletBinding = DataBindingUtil.inflate(inflater, R.layout.bus_frag01_ticket, container, false)
		ticketsAdapter = TicketsAdapter()
		biletBinding.recyclerViewBilete.adapter = ticketsAdapter
		return biletBinding.root
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		initSharedViewModel(BusViewModel::class.java, BusViewModel.getFactory(eTicketApp))
		subscribeToObservers()
	}

	private fun subscribeToObservers() {
		sharedViewModel.busTicketsLiveData.observe(viewLifecycleOwner, Observer { list ->
			biletBinding.isLoading = false
			ticketsAdapter.tickets = list
		})
	}

	companion object {
		private const val TAG = "FrgTb01Ticket"
		const val ARG_PAGE_INDEX = "arg_page_index"

		fun newInstance() = FrgTb01Ticket()
		fun newInstance(page: Int, title: String?): FrgTb01Ticket {
			return FrgTb01Ticket().apply {
				arguments = Bundle().apply {
					putInt(ARG_PAGE_INDEX, page)
					putString("title", title)
				}
			}
		}
	}
}