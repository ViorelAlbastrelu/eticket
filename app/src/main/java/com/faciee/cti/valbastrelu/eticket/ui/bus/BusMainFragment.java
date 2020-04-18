package com.faciee.cti.valbastrelu.eticket.ui.bus;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
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
import com.faciee.cti.valbastrelu.eticket.base.ETicketApp;
import com.faciee.cti.valbastrelu.eticket.databinding.FragmentBusMainBinding;
import com.faciee.cti.valbastrelu.eticket.room.entities.Route;
import com.faciee.cti.valbastrelu.eticket.ui.bus.model.BusViewModel;
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.SectionsPagerAdapter;


public class BusMainFragment extends BaseFragment<BusViewModel> {
	private static final String TAG = "BusActivity";
	private FragmentBusMainBinding busBinding;
	NfcAdapter nfcAdapter;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		busBinding = FragmentBusMainBinding.inflate(inflater, container, false);
		initViewModel(BusViewModel.class, BusViewModel.getFactory(getETicketApp()));

		//TODO set toolbar title in parent activity maybe using navigation
		//		busBinding.fab.setOnClickListener(v -> busActivityModel.insertBilet(new Bilet(102, true, 2, 2)));
		nfcAdapter = NfcAdapter.getDefaultAdapter(getContext());
		setupViewPager(busBinding.viewPager);
		busBinding.tabs.setupWithViewPager(busBinding.viewPager);
		return busBinding.getRoot();
	}

	@Override
	public void onResume() {
		super.onResume();
		//TODO enable or disable if on emulator
//		enableForegroundDispatch();
	}

	@Override
	public void onPause() {
		super.onPause();
//		disableForegroundDispatch();
	}

//	@Override
//	protected void onNewIntent(Intent intent) {
//		super.onNewIntent(intent);
//		if (intent.hasExtra(NfcAdapter.EXTRA_TAG)) {
//			ETicketApp.toastMessageShort("Bilet activat!");
//			viewModel.insertBilet(new Bilet(44, true, 2, 2));
//			Parcelable[] parcelables = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
//			if (parcelables != null && parcelables.length > 0) {
//				NfcMessageComposer.readTextFromTag((NdefMessage) parcelables[0]);
//			}
//			Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
//			NdefMessage message = NfcMessageComposer.createNdefMessage("Bilet 100");
//			NfcMessageComposer.writeNdefMessage(tag, message);
//		}
//	}

	private void enableForegroundDispatch() {
		Intent intent = new Intent(getActivity(), BusMainFragment.class).addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
		PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intent, 0);
		IntentFilter[] intentFilters = new IntentFilter[]{};
		nfcAdapter.enableForegroundDispatch(getActivity(), pendingIntent, intentFilters, null);
	}

	private void disableForegroundDispatch() {
		nfcAdapter.disableForegroundDispatch(getActivity());
	}

	private void setupViewPager(ViewPager viewPager) {
		SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
		sectionsPagerAdapter.addFragment(new FrgTb01Bilet(), getString(R.string.tab_name_ticket));    //BILETE
		sectionsPagerAdapter.addFragment(new FrgTb02Trasee(), getString(R.string.tab_name_routes));   //TRASEE
		sectionsPagerAdapter.addFragment(new FrgTb03Istoric(), getString(R.string.tab_name_history)); //ISTORIC
		viewPager.setAdapter(sectionsPagerAdapter);
	}

	public void showStatiiForTraseu(Route route) {
		FrgTb02Statii frgTb02Statii = FrgTb02Statii.statiiPentruTraseu(route.getNumber());
		Log.d(TAG, "showStatiiForTraseu: Called!");
		getParentFragmentManager()
				.beginTransaction()
				.addToBackStack("product")
				.replace(R.id.viewPager,
						frgTb02Statii, null).commit();
	}
}
