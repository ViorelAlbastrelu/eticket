package com.faciee.cti.valbastrelu.eticket.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

public abstract class BaseFragment<VM extends ViewModel> extends Fragment {
	protected @Nullable VM viewModel;

	protected void navigateTo(@IdRes int destination) {
		navigateTo(destination, null);
	}

	protected void navigateTo(@IdRes int destination, @Nullable Bundle args){
		View view = getView();
		if (view != null)
			Navigation.findNavController(view).navigate(destination, args);
	}
}
