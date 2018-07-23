package com.faciee.cti.valbastrelu.eticket.ui.parking;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.main.ETicketApp;
import com.faciee.cti.valbastrelu.eticket.room.entities.BiletP;
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.SectionsPagerAdapter;
import com.faciee.cti.valbastrelu.eticket.ui.parking.model.ParkingActivityModel;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ParkingActivity extends AppCompatActivity {
	private static final String TAG = "ParkingActivity";
	private static final int ERROR_DIALOG_REQUEST = 9001;
	
	private ParkingActivityModel parkingActivityModel;
	
	@BindView(R.id.container)
	ViewPager mViewPager;
	@BindView(R.id.tabs)
	TabLayout mTabLayout;
	
	private SectionsPagerAdapter mSectionsPagerAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parking);
		ButterKnife.bind(this);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle(R.string.menu_car);
		setSupportActionBar(toolbar);
		isServiceOK();
		mViewPager.setAdapter(mSectionsPagerAdapter);
		setupViewPager(mViewPager);
		mTabLayout.setupWithViewPager(mViewPager);
		parkingActivityModel = ViewModelProviders.of(this).get(ParkingActivityModel.class);
	}
	
	@OnClick(R.id.fab)
	void onClickFab(View view) {
//		Snackbar.make(view, "Bilet adaugat", Snackbar.LENGTH_LONG)
//				.setAction("Action", null).show();
		parkingActivityModel.insertBilet(new BiletP("Mazepa", true, 1.5, false));
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
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		mSectionsPagerAdapter.addFragment(new FrgTb01Bilet(), getApplication().getString(R.string.tab_name_ticket));    //BILETE
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
