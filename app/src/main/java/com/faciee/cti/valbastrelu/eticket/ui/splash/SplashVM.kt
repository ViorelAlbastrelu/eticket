package com.faciee.cti.valbastrelu.eticket.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.faciee.cti.valbastrelu.eticket.api.Schedule
import com.faciee.cti.valbastrelu.eticket.base.ETicketApp
import com.faciee.cti.valbastrelu.eticket.repo.SplashRepository
import com.faciee.cti.valbastrelu.eticket.util.AppUtils
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashVM(
		private val app: ETicketApp,
		private val splashRepo: SplashRepository
) : ViewModel() {

	val isLoading = MutableLiveData<Boolean>()

	init {
		viewModelScope.launch {
			isLoading.postValue(true)
			delay(500)
			if (app.appPreferences.scheduleCache.not()) {
				val deferred = async {
					val loadJSONFromAsset = AppUtils.loadJSONFromAsset(app.assets, "transurb_schedule.json")
					var schedule: Schedule? = null
					if (! loadJSONFromAsset.isNullOrBlank()) {
						schedule = AppUtils.getObjectFromJSON(loadJSONFromAsset, Schedule::class.java)
					}
					return@async schedule
				}
				splashRepo.insertSchedules(deferred.await())
			}
		}.invokeOnCompletion {
			isLoading.postValue(false)
			app.appPreferences.scheduleCache = true
		}
	}

	companion object {
		private const val TAG = "SplashVM"

		fun getFactory(app: ETicketApp, repo: SplashRepository): ViewModelProvider.Factory {
			return object : ViewModelProvider.Factory {
				@Suppress("UNCHECKED_CAST")
				override fun <T : ViewModel?> create(modelClass: Class<T>): T {
					return SplashVM(app, repo) as T
				}
			}
		}
	}
}