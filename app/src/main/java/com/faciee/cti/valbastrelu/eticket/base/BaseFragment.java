package com.faciee.cti.valbastrelu.eticket.base;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

public class BaseFragment<VM extends ViewModel> extends Fragment {
	protected @Nullable VM viewModel;
}
