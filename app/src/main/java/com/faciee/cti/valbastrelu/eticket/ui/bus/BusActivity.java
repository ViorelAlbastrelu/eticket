package com.faciee.cti.valbastrelu.eticket.ui.bus;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.databinding.ActivityBusBinding;
import com.faciee.cti.valbastrelu.eticket.main.ETicketApp;
import com.faciee.cti.valbastrelu.eticket.room.entities.Bilet;
import com.faciee.cti.valbastrelu.eticket.room.entities.Traseu;
import com.faciee.cti.valbastrelu.eticket.ui.bus.model.BusActivityViewModel;
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.SectionsPagerAdapter;


public class BusActivity extends AppCompatActivity{
	private static final String TAG = "BusActivity";
	private ActivityBusBinding busBinding;
	BusActivityViewModel busActivityModel;
	NfcAdapter nfcAdapter;
	
	SectionsPagerAdapter sectionsPagerAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		busBinding = ActivityBusBinding.inflate(getLayoutInflater());
		setContentView(busBinding.getRoot());
		busBinding.fab.setOnClickListener(view -> busActivityModel.insertBilet(new Bilet(102, true, 2, 2)));
		busBinding.includeToolbar.toolbar.setTitle(R.string.menu_bus);
		setSupportActionBar(busBinding.includeToolbar.toolbar);
		busActivityModel = ViewModelProviders.of(this).get(BusActivityViewModel.class);
		nfcAdapter = NfcAdapter.getDefaultAdapter(this);
		setupViewPager(busBinding.viewPager);
		busBinding.tabs.setupWithViewPager(busBinding.viewPager);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		//TODO enable or disable if on emulator
//		enableForegroundDispatch();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
//		disableForegroundDispatch();
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		if (intent.hasExtra(NfcAdapter.EXTRA_TAG)) {
			ETicketApp.toastMessageShort("Bilet activat!");
			busActivityModel.insertBilet(new Bilet(44, true, 2, 2));
//			Parcelable[] parcelables = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
//			if (parcelables != null && parcelables.length > 0) {
//				NfcMessageComposer.readTextFromTag((NdefMessage) parcelables[0]);
//			}
//			Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
//			NdefMessage message = NfcMessageComposer.createNdefMessage("Bilet 100");
//			NfcMessageComposer.writeNdefMessage(tag, message);
		}
		
	}
	
	private void enableForegroundDispatch() {
		Intent intent = new Intent(this, BusActivity.class).addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
		IntentFilter[] intentFilters = new IntentFilter[]{};
		nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilters, null);
	}
	
	private void disableForegroundDispatch() {
		nfcAdapter.disableForegroundDispatch(this);
	}
	
	private void setupViewPager(ViewPager viewPager) {
		sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		sectionsPagerAdapter.addFragment(new FrgTb01Bilet(), getApplication().getString(R.string.tab_name_ticket));    //BILETE
		sectionsPagerAdapter.addFragment(new FrgTb02Trasee(), getApplication().getString(R.string.tab_name_routes));   //TRASEE
		sectionsPagerAdapter.addFragment(new FrgTb03Istoric(), getApplication().getString(R.string.tab_name_history)); //ISTORIC
		viewPager.setAdapter(sectionsPagerAdapter);
	}
	
	public void showStatiiForTraseu(Traseu traseu) {
		FrgTb02Statii frgTb02Statii = FrgTb02Statii.statiiPentruTraseu(traseu.getNrTraseu());
		Log.d(TAG, "showStatiiForTraseu: Called!");
		getSupportFragmentManager()
				.beginTransaction()
				.addToBackStack("product")
				.replace(R.id.viewPager,
						frgTb02Statii, null).commit();
	}
}
