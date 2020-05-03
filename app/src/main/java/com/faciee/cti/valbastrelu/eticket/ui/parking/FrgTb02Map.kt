package com.faciee.cti.valbastrelu.eticket.ui.parking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.faciee.cti.valbastrelu.eticket.R
import com.faciee.cti.valbastrelu.eticket.base.MapBaseFragment
import com.faciee.cti.valbastrelu.eticket.base.SharingFragment
import com.faciee.cti.valbastrelu.eticket.databinding.ParkingFrgTb02MapBinding
import com.faciee.cti.valbastrelu.eticket.ui.parking.model.ParkingViewModel
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.parking_frg_tb02_map.*

class FrgTb02Map : MapBaseFragment<ParkingViewModel>() {

	private lateinit var parcariBinding: ParkingFrgTb02MapBinding

	override val mapId: Int
		get() = R.id.mapHost

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
							  savedInstanceState: Bundle?): View? {
		super.onCreateView(inflater, container, savedInstanceState)
		parcariBinding = ParkingFrgTb02MapBinding.inflate(layoutInflater, container, false)

//		val description = "Strada: Domnească \nLocuri: 3/25 \nCost taxă: 1.5 RON/h"
//		parcariBinding.title.text = "POI: Parcare Nespălata"
//		parcariBinding.content.text = description
//		parcariBinding.pinIcon.setOnClickListener { v: View? ->
//			parcariBinding.parkingDescription.isVisible = ! parcariBinding.parkingDescription.isVisible
//		}
		return parcariBinding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		mapFragment = childFragmentManager.findFragmentById(mapId) as SupportMapFragment
		mapFragment.getMapAsync(this)
	}

	companion object {
		private const val TAG = "FrgTb02Map"

	}
}