package com.faciee.cti.valbastrelu.eticket.ui.parking

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.faciee.cti.valbastrelu.eticket.R
import com.faciee.cti.valbastrelu.eticket.base.BaseFragment
import com.faciee.cti.valbastrelu.eticket.base.ETicketApp.Companion.toastMessageShort
import com.faciee.cti.valbastrelu.eticket.databinding.FragmentParkingMainBinding
import com.faciee.cti.valbastrelu.eticket.room.entities.TicketParking
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.SectionsPagerAdapter
import com.faciee.cti.valbastrelu.eticket.ui.history.FrgTransactionHistory.Companion.newInstance
import com.faciee.cti.valbastrelu.eticket.ui.parking.model.ParkingViewModel
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import java.math.BigDecimal

class FragmentParkingMain : BaseFragment<ParkingViewModel>() {

	private lateinit var parkingBinding: FragmentParkingMainBinding

	private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		parkingBinding = FragmentParkingMainBinding.inflate(inflater, container, false)
		parkingBinding.viewPager.adapter = mSectionsPagerAdapter
		setupViewPager(parkingBinding.viewPager)
		parkingBinding.tabs.setupWithViewPager(parkingBinding.viewPager)
		initViewModel(ParkingViewModel::class.java, ParkingViewModel.getFactory(eTicketApp))
		return parkingBinding.root
	}

	fun onClickFab(view: View?) { //		Snackbar.make(view, "Bilet adaugat", Snackbar.LENGTH_LONG)
//				.setAction("Action", null).show();
		viewModel.insertParkingTicket(TicketParking("Mazepa", true, BigDecimal(1.5), false))
	}

	private fun setupViewPager(viewPager: ViewPager) {
		mSectionsPagerAdapter = SectionsPagerAdapter(childFragmentManager)
		mSectionsPagerAdapter !!.addFragment(FrgTb01Tickets.newInstance(), getString(R.string.tab_name_ticket)) //BILETE
		mSectionsPagerAdapter !!.addFragment(FrgTb02Map(), getString(R.string.tab_name_parking)) //PARCARI
		mSectionsPagerAdapter !!.addFragment(newInstance(), getString(R.string.tab_name_history)) //ISTORIC
		viewPager.adapter = mSectionsPagerAdapter
	}

	private val isServiceOK: Boolean
		get() {
			Log.d(TAG, "isServiceOK: check google services version")
			val googleApiAvailability = GoogleApiAvailability.getInstance()
			val available = googleApiAvailability.isGooglePlayServicesAvailable(context)
			when {
				available == ConnectionResult.SUCCESS -> {
					Log.d(TAG, "isServiceOK: Google Play Services working")
					return true
				}
				googleApiAvailability.isUserResolvableError(available) -> {
					Log.d(TAG, "isServiceOK: Error can be fixed")
					val dialog = googleApiAvailability.getErrorDialog(activity, available, ERROR_DIALOG_REQUEST)
					dialog.show()
				}
				else -> toastMessageShort("You can't make map requests")
			}
			return false
		}

	companion object {
		private const val TAG = "FragmentParkingMain"
		private const val ERROR_DIALOG_REQUEST = 9001
	}
}