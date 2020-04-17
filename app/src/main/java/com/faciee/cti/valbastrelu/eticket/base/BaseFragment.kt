package com.faciee.cti.valbastrelu.eticket.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation

abstract class BaseFragment<VM : ViewModel> : Fragment() {

	var viewModel: VM? = null

	protected fun navigateTo(@IdRes destination: Int, args: Bundle? = null) {
		val view = view
		if (view != null) Navigation.findNavController(view).navigate(destination, args)
	}

	protected fun initViewModel(viewModelClass: Class<VM>) {
		viewModel = ViewModelProvider(this).get(viewModelClass)
	}
}