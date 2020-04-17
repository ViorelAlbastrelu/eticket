package com.faciee.cti.valbastrelu.eticket.ui.parking;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.base.BaseFragment;
import com.faciee.cti.valbastrelu.eticket.databinding.FragmentParkingMainBinding;
import com.faciee.cti.valbastrelu.eticket.base.ETicketApp;
import com.faciee.cti.valbastrelu.eticket.room.entities.BiletP;
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.SectionsPagerAdapter;
import com.faciee.cti.valbastrelu.eticket.ui.common.i.UpdateRecyclerViewCallback;
import com.faciee.cti.valbastrelu.eticket.ui.parking.model.ParkingViewModel;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;


public class FragmentParkingMain extends BaseFragment<ParkingViewModel> {
	private static final String TAG = "ParkingActivity";
	private static final int ERROR_DIALOG_REQUEST = 9001;

	private UpdateRecyclerViewCallback recyclerViewCallback;
	private FragmentParkingMainBinding parkingBinding;
	private SectionsPagerAdapter mSectionsPagerAdapter;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		parkingBinding = FragmentParkingMainBinding.inflate(inflater, container, false);

//		parkingBinding.fab.setOnClickListener(this::onClickFab);

		parkingBinding.viewPager.setAdapter(mSectionsPagerAdapter);
		setupViewPager(parkingBinding.viewPager);
		parkingBinding.tabs.setupWithViewPager(parkingBinding.viewPager);
		initViewModel(ParkingViewModel.class);
		return parkingBinding.getRoot();
	}

	void onClickFab(View view) {
//		Snackbar.make(view, "Bilet adaugat", Snackbar.LENGTH_LONG)
//				.setAction("Action", null).show();
		getViewModel().insertBilet(new BiletP("Mazepa", true, 1.5, false));
		recyclerViewCallback.scrollToTop();
	}

	private void setupViewPager(ViewPager viewPager) {
		FrgTb01Bilet frgTb01Bilet = new FrgTb01Bilet();
		recyclerViewCallback = frgTb01Bilet.getRecycleViewCallback();
		mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
		mSectionsPagerAdapter.addFragment(frgTb01Bilet, getString(R.string.tab_name_ticket));    //BILETE
		mSectionsPagerAdapter.addFragment(new FrgTb02Parcari(), getString(R.string.tab_name_parking));   //PARCARI
		mSectionsPagerAdapter.addFragment(new FrgTb03Istoric(), getString(R.string.tab_name_history)); //ISTORIC
		viewPager.setAdapter(mSectionsPagerAdapter);
	}
	
	private boolean isServiceOK() {
		Log.d(TAG, "isServiceOK: check google services version");
		int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getContext());
		if (available == ConnectionResult.SUCCESS) {
			Log.d(TAG, "isServiceOK: Google Play Services working");
			return true;
		} else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
			Log.d(TAG, "isServiceOK: Error can be fixed");
			Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(getActivity(), available, ERROR_DIALOG_REQUEST);
			dialog.show();
		} else
			ETicketApp.toastMessageShort("You can't make map requests");
		return false;
	}
}
