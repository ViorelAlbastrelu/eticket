package com.faciee.cti.valbastrelu.eticket.ui.parking;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.databinding.ActivityParkingBinding;
import com.faciee.cti.valbastrelu.eticket.main.ETicketApp;
import com.faciee.cti.valbastrelu.eticket.room.entities.BiletP;
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.SectionsPagerAdapter;
import com.faciee.cti.valbastrelu.eticket.ui.common.i.UpdateRecyclerViewCallback;
import com.faciee.cti.valbastrelu.eticket.ui.parking.model.ParkingActivityViewModel;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;


public class ParkingActivity extends AppCompatActivity {
	private static final String TAG = "ParkingActivity";
	private static final int ERROR_DIALOG_REQUEST = 9001;
	private UpdateRecyclerViewCallback recyclerViewCallback;

	private ActivityParkingBinding parkingBinding;
	private ParkingActivityViewModel parkingActivityModel;

	private SectionsPagerAdapter mSectionsPagerAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		parkingBinding = ActivityParkingBinding.inflate(getLayoutInflater());
		setContentView(parkingBinding.getRoot());
		parkingBinding.fab.setOnClickListener(this::onClickFab);

		parkingBinding.toolbar.setTitle(R.string.menu_car);
		setActionBar(parkingBinding.toolbar);
		parkingBinding.viewPager.setAdapter(mSectionsPagerAdapter);
		setupViewPager(parkingBinding.viewPager);
		parkingBinding.tabs.setupWithViewPager(parkingBinding.viewPager);
		parkingActivityModel = new ViewModelProvider(this).get(ParkingActivityViewModel.class);
	}



	void onClickFab(View view) {
//		Snackbar.make(view, "Bilet adaugat", Snackbar.LENGTH_LONG)
//				.setAction("Action", null).show();
		parkingActivityModel.insertBilet(new BiletP("Mazepa", true, 1.5, false));
		recyclerViewCallback.scrollToTop();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_parking, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		
		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	private void setupViewPager(ViewPager viewPager) {
		FrgTb01Bilet frgTb01Bilet = new FrgTb01Bilet();
		recyclerViewCallback = frgTb01Bilet.getRecycleViewCallback();
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		mSectionsPagerAdapter.addFragment(frgTb01Bilet, getApplication().getString(R.string.tab_name_ticket));    //BILETE
		mSectionsPagerAdapter.addFragment(new FrgTb02Parcari(), getApplication().getString(R.string.tab_name_parking));   //PARCARI
		mSectionsPagerAdapter.addFragment(new FrgTb03Istoric(), getApplication().getString(R.string.tab_name_history)); //ISTORIC
		viewPager.setAdapter(mSectionsPagerAdapter);
	}
	
	private boolean isServiceOK() {
		Log.d(TAG, "isServiceOK: check google services version");
		int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
		if (available == ConnectionResult.SUCCESS) {
			Log.d(TAG, "isServiceOK: Google Play Services working");
			return true;
		} else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
			Log.d(TAG, "isServiceOK: Error can be fixed");
			Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(this, available, ERROR_DIALOG_REQUEST);
			dialog.show();
		} else
			ETicketApp.toastMessageShort("You can't make map requests");
		return false;
	}
}
