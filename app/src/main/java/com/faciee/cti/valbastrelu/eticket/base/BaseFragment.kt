package com.faciee.cti.valbastrelu.eticket.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation

abstract class BaseFragment<VM : ViewModel> : Fragment() {

	protected lateinit var eTicketApp: ETicketApp
	protected lateinit var viewModel: VM

	override fun onAttach(context: Context) {
		super.onAttach(context)
		eTicketApp = (activity as BaseActivity).eTicketApp
	}

	protected fun navigateTo(@IdRes destination: Int, args: Bundle? = null) {
		val view = view
		if (view != null) Navigation.findNavController(view).navigate(destination, args)
	}

	protected fun initViewModel(viewModelClass: Class<VM>, factory: ViewModelProvider.Factory? = null) {
		if (factory != null)
			viewModel = ViewModelProvider(this, factory).get(viewModelClass)
		else
			viewModel = ViewModelProvider(this).get(viewModelClass)
	}
}