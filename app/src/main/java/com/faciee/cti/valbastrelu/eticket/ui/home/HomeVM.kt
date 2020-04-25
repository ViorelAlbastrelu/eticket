package com.faciee.cti.valbastrelu.eticket.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.faciee.cti.valbastrelu.eticket.base.AbstractAndroidViewModel
import com.faciee.cti.valbastrelu.eticket.base.ETicketApp
import com.faciee.cti.valbastrelu.eticket.repo.HomeRepository
import kotlinx.coroutines.launch

class HomeVM(
		app: ETicketApp,
		private val repository: HomeRepository
) : AbstractAndroidViewModel(app) {

	var feedItems: MutableLiveData<List<Any>> = MutableLiveData()

	init {
		viewModelScope.launch {
			repository.insertTicketInDatabase()
		}
		feedItems = feedLiveData()
	}

	override fun onCleared() {
		super.onCleared()
		viewModelScope.launch {
			repository.clearDB()
		}
	}

	fun refreshItems() {
		feedItems = feedLiveData()
	}

	private fun feedLiveData(): MutableLiveData<List<Any>> = liveData {
		val latestActiveTickets = repository.getLatestActiveTickets()
		emit(latestActiveTickets)
	} as MutableLiveData

	companion object {
		private const val TAG = "BusActivityModel"

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
