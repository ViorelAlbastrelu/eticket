package com.faciee.cti.valbastrelu.eticket.ui.bus.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.faciee.cti.valbastrelu.eticket.base.AbstractAndroidViewModel
import com.faciee.cti.valbastrelu.eticket.base.ETicketApp
import com.faciee.cti.valbastrelu.eticket.repo.ETkBusRepository
import com.faciee.cti.valbastrelu.eticket.room.entities.Route
import com.faciee.cti.valbastrelu.eticket.room.entities.Ticket
import com.faciee.cti.valbastrelu.eticket.room.entities.Transaction

class BusViewModel(application: ETicketApp) : AbstractAndroidViewModel(application) {
	private val repository: ETkBusRepository? = application.busRepository
	private var ticketActiv: Ticket? = null
	var liveDataBilete: LiveData<List<Ticket>>

	init {
		//TODO investigate this
		liveDataBilete = repository!!.bilete
	}


	fun setTicketActiv(ticketActiv: Ticket?) {
		this.ticketActiv = ticketActiv
	}

	val liveDataTrasee: LiveData<List<Route>>
		get() = repository !!.liveDataTrasee

	fun getLiveDataStatii(nrTraseu: Int): LiveData<List<String>> {
		return repository !!.getLiveDataStatii(nrTraseu)
	}

	val liveDataTranzactii: LiveData<List<Transaction?>?>?
		get() = repository !!.liveDataTranzactii

	//INSERTS
	fun insertBilet(ticket: Ticket?) {
		setTicketActiv(ticket)
		repository !!.insertBilet(ticket)
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