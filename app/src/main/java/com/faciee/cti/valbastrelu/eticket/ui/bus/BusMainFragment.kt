package com.faciee.cti.valbastrelu.eticket.ui.bus

import android.app.PendingIntent
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.viewpager.widget.ViewPager
import com.faciee.cti.valbastrelu.eticket.R
import com.faciee.cti.valbastrelu.eticket.base.BaseFragment
import com.faciee.cti.valbastrelu.eticket.databinding.FragmentBusMainBinding
import com.faciee.cti.valbastrelu.eticket.ui.bus.vm.BusViewModel
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.SectionsPagerAdapter
import com.faciee.cti.valbastrelu.eticket.ui.history.FrgTransactionHistory

class BusMainFragment : BaseFragment<BusViewModel>() {

	private lateinit var busBinding: FragmentBusMainBinding
	var nfcAdapter: NfcAdapter? = null

	lateinit var searchView: SearchView
	lateinit var queryTextListener: SearchView.OnQueryTextListener

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setHasOptionsMenu(true)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		busBinding = FragmentBusMainBinding.inflate(inflater, container, false)
		initViewModel(BusViewModel::class.java, BusViewModel.getFactory(eTicketApp))

		nfcAdapter = NfcAdapter.getDefaultAdapter(context)
		setupViewPager(busBinding.viewPager)
		busBinding.tabs.setupWithViewPager(busBinding.viewPager)
		return busBinding.root
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		inflater.inflate(R.menu.eticket_main, menu)
		val searchItem = menu.findItem(R.id.action_search)

		val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager

		searchItem?.let {
			searchView = it.actionView as SearchView
			searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))

			queryTextListener = object : SearchView.OnQueryTextListener {
				override fun onQueryTextSubmit(query: String?): Boolean {
					Log.i(TAG, "onQueryTextSubmit: $query")
					return true
				}

				override fun onQueryTextChange(newText: String?): Boolean {
					Log.i(TAG, "onQueryTextChange: $newText")
					return true
				}
			}

			searchView.setOnQueryTextListener(queryTextListener)
		}
		super.onCreateOptionsMenu(menu, inflater)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.action_search -> {
				return false
			}
			else -> {
				//Noop
			}
		}
		searchView.setOnQueryTextListener(queryTextListener)
		return super.onOptionsItemSelected(item)
	}

	override fun onResume() {
		super.onResume()
		//TODO enable or disable if on emulator
//		enableForegroundDispatch();
	}

	override fun onPause() {
		super.onPause()
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
	private fun enableForegroundDispatch() {
		val intent = Intent(activity, BusMainFragment::class.java).addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING)
		val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
		val intentFilters = arrayOf<IntentFilter>()
		nfcAdapter!!.enableForegroundDispatch(activity, pendingIntent, intentFilters, null)
	}

	private fun disableForegroundDispatch() {
		nfcAdapter!!.disableForegroundDispatch(activity)
	}

	private fun setupViewPager(viewPager: ViewPager) {
		val sectionsPagerAdapter = SectionsPagerAdapter(childFragmentManager)
		sectionsPagerAdapter.addFragment(FrgTb01Ticket.newInstance(), getString(R.string.tab_name_ticket)) //BILETE
		sectionsPagerAdapter.addFragment(FrgTb02Routes.newInstance(), getString(R.string.tab_name_routes)) //TRASEE
		sectionsPagerAdapter.addFragment(FrgTransactionHistory.newInstance(), getString(R.string.tab_name_history)) //ISTORIC
		viewPager.adapter = sectionsPagerAdapter
	}

	fun showStatiiForTraseu(routeNumber: Int) {
		val frgTb02Statii = FrgTb02Stations.newInstance(routeNumber)
		Log.d(TAG, "showStatiiForTraseu: Called!")
		parentFragmentManager
				.beginTransaction()
				.addToBackStack("product")
				.replace(R.id.viewPager,
						frgTb02Statii, null).commit()
	}

	companion object {
		private const val TAG = "BusActivity"
	}
}