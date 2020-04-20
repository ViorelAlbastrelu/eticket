package com.faciee.cti.valbastrelu.eticket.ui.parking.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.faciee.cti.valbastrelu.eticket.base.AbstractAndroidViewModel
import com.faciee.cti.valbastrelu.eticket.base.ETicketApp
import com.faciee.cti.valbastrelu.eticket.repo.EtkParkingRepository
import com.faciee.cti.valbastrelu.eticket.room.entities.TicketParking
import com.faciee.cti.valbastrelu.eticket.room.entities.Transaction

class ParkingViewModel(application: ETicketApp) : AbstractAndroidViewModel(application) {

	private val repository: EtkParkingRepository? = application.parkingRepository

	var ticketsLiveData: LiveData<List<TicketParking>>
		get() = repository !!.parkingTickets

	val liveDataTransactions: LiveData<List<Transaction>>
		get() = repository !!.liveDataTranzactii

	fun insertParkingTicket(ticketParking: TicketParking) {
		repository !!.insertBilet(ticketParking)
	}

	init {
		ticketsLiveData = repository !!.parkingTickets
	}

	companion object {
		private const val TAG = "ParkingViewModel"

		fun getFactory(app: ETicketApp): ViewModelProvider.Factory {
			return object : ViewModelProvider.Factory {
				@Suppress("UNCHECKED_CAST")
				override fun <T : ViewModel?> create(modelClass: Class<T>): T {
					return ParkingViewModel(app) as T
				}
			}
		}
	}
}