package com.faciee.cti.valbastrelu.eticket.ui.bus.vm

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.faciee.cti.valbastrelu.eticket.base.AbstractAndroidViewModel
import com.faciee.cti.valbastrelu.eticket.base.ETicketApp
import com.faciee.cti.valbastrelu.eticket.repo.BusRepository
import com.faciee.cti.valbastrelu.eticket.room.entities.Route
import com.faciee.cti.valbastrelu.eticket.room.entities.Ticket
import com.faciee.cti.valbastrelu.eticket.room.entities.Transaction
import com.faciee.cti.valbastrelu.eticket.ui.common.TransportType
import kotlinx.coroutines.launch

class BusViewModel(
		application: ETicketApp
) : AbstractAndroidViewModel(application) {

	@VisibleForTesting
	constructor(app: ETicketApp, repository: BusRepository) : this(app) {
		this.repository = repository
	}

	private var repository: BusRepository
	private var activeTicket: Ticket? = null
	var busTicketsLiveData: LiveData<List<Ticket>>

	val liveDataTrasee: MutableLiveData<List<Route>> = MutableLiveData()


	init {
		repository = application.busRepository
		busTicketsLiveData = repository.ticketsLiveData

		viewModelScope.launch {
			liveDataTrasee.value = repository.getRoutes(TransportType.BUS)
		}
	}

	fun setTicketActiv(ticketActiv: Ticket?) {
		this.activeTicket = ticketActiv
	}

	fun getLiveDataStatii(nrTraseu: Int): LiveData<List<String>> {
		return repository.getLiveDataStatii(nrTraseu)
	}

	val liveDataTranzactii: LiveData<List<Transaction>>
		get() = repository.liveDataTranzactii

	fun saveTicket(ticket: Ticket) {
		setTicketActiv(ticket)
		viewModelScope.launch {
			repository.insertTicketInDatabase(ticket)
		}
	}

	override fun onCleared() {
		super.onCleared()
		Log.d(TAG, "onCleared: called")
	}

	companion object {
		private const val TAG = "BusActivityModel"

		fun getFactory(app: ETicketApp): ViewModelProvider.Factory {
			return object : ViewModelProvider.Factory {
				@Suppress("UNCHECKED_CAST")
				override fun <T : ViewModel?> create(modelClass: Class<T>): T {
					return BusViewModel(app) as T
				}
			}
		}
	}
}