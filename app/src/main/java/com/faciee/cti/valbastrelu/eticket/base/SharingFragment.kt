package com.faciee.cti.valbastrelu.eticket.base

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class SharingFragment<VM : AndroidViewModel, SVM : ViewModel> : BaseFragment<VM>() {

	lateinit var sharedViewModel: SVM

	protected fun initSharedViewModel(sharedViewModelClass: Class<SVM>, factory: ViewModelProvider.Factory? = null) {
		activity?.let {
			if (factory != null)
				sharedViewModel = ViewModelProvider(it, factory).get(sharedViewModelClass)
			else
				sharedViewModel = ViewModelProvider(it).get(sharedViewModelClass)
		}
	}
}