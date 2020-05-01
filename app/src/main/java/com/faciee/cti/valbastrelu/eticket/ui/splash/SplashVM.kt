package com.faciee.cti.valbastrelu.eticket.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.faciee.cti.valbastrelu.eticket.api.Schedule
import com.faciee.cti.valbastrelu.eticket.base.ETicketApp
import com.faciee.cti.valbastrelu.eticket.util.AppUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashVM(
		private val app: ETicketApp
) : ViewModel() {

	val isLoading = MutableLiveData<Boolean>()

	init {
		val job = CoroutineScope(Dispatchers.IO).launch {
			isLoading.postValue(true)
			val loadJSONFromAsset = AppUtils.loadJSONFromAsset(app.assets, "transurb_schedule.json")
			if (! loadJSONFromAsset.isNullOrBlank()) {
				val objectFromJSON = AppUtils.getObjectFromJSON(loadJSONFromAsset, Schedule::class.java)
				objectFromJSON.bus
			}
		}
		job.invokeOnCompletion { isLoading.postValue(false) }
	}

	companion object {
		private const val TAG = "SplashVM"

		fun getFactory(app: ETicketApp): ViewModelProvider.Factory {
			return object : ViewModelProvider.Factory {
				@Suppress("UNCHECKED_CAST")
				override fun <T : ViewModel?> create(modelClass: Class<T>): T {
					return SplashVM(app) as T
				}
			}
		}
	}
}