package com.faciee.cti.valbastrelu.eticket.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.faciee.cti.valbastrelu.eticket.api.Schedule
import com.faciee.cti.valbastrelu.eticket.base.AbstractAndroidViewModel
import com.faciee.cti.valbastrelu.eticket.base.ETicketApp
import com.faciee.cti.valbastrelu.eticket.repo.HomeRepository
import com.faciee.cti.valbastrelu.eticket.room.entities.Ticket
import com.faciee.cti.valbastrelu.eticket.util.AppUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeVM(
		private val app: ETicketApp,
		private val repository: HomeRepository
) : AbstractAndroidViewModel(app) {

	var feedItems: MutableLiveData<List<Any>> = MutableLiveData()

	init {
		viewModelScope.launch {
			repository.insertTicketInDatabase()
			feedItems.value = repository.getLatestActiveTickets()
		}
	}

	override fun onCleared() {
		super.onCleared()
		viewModelScope.launch {
			repository.clearDB()
		}
	}

	fun refreshItems() {
		viewModelScope.launch {
			feedItems.value = repository.getLatestActiveTickets()
		}
	}

	companion object {
		private const val TAG = "HomeVM"

		fun getFactory(app: ETicketApp, repository: HomeRepository): ViewModelProvider.Factory {
			return object : ViewModelProvider.Factory {
				@Suppress("UNCHECKED_CAST")
				override fun <T : ViewModel?> create(modelClass: Class<T>): T {
					return HomeVM(app, repository) as T
				}
			}
		}
	}
}
