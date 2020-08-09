package com.faciee.cti.valbastrelu.eticket.ui.parking

import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.faciee.cti.valbastrelu.eticket.R
import com.faciee.cti.valbastrelu.eticket.base.MapBaseFragment
import com.faciee.cti.valbastrelu.eticket.databinding.ParkingFrgTb02MapBinding
import com.faciee.cti.valbastrelu.eticket.ui.parking.model.ParkingViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng

class FrgTb02Map : MapBaseFragment<ParkingViewModel>() {

	private lateinit var parkingBinding: ParkingFrgTb02MapBinding

	override val mapId: Int
		get() = R.id.mapHost

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
							  savedInstanceState: Bundle?): View? {
		super.onCreateView(inflater, container, savedInstanceState)
		parkingBinding = ParkingFrgTb02MapBinding.inflate(inflater, container, false)

//		val description = "Strada: Domnească \nLocuri: 3/25 \nCost taxă: 1.5 RON/h"
//		parcariBinding.title.text = "POI: Parcare Nespălata"
//		parcariBinding.content.text = description
//		parcariBinding.pinIcon.setOnClickListener { v: View? ->
//			parcariBinding.parkingDescription.isVisible = ! parcariBinding.parkingDescription.isVisible
//		}
		return parkingBinding.root
	}

	companion object {
		private const val TAG = "FrgTb02Map"

	}
}