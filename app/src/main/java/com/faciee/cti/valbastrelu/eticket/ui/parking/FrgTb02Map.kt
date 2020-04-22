package com.faciee.cti.valbastrelu.eticket.ui.parking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.faciee.cti.valbastrelu.eticket.base.SharingFragment
import com.faciee.cti.valbastrelu.eticket.databinding.ParkingFrgTb02MapBinding
import com.faciee.cti.valbastrelu.eticket.ui.parking.model.ParkingViewModel

class FrgTb02Map : SharingFragment<ParkingViewModel, ParkingViewModel>() {
	private lateinit var parcariBinding: ParkingFrgTb02MapBinding

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
							  savedInstanceState: Bundle?): View? {
		parcariBinding = ParkingFrgTb02MapBinding.inflate(layoutInflater, container, false)

		val description = "Strada: Domnească \nLocuri: 3/25 \nCost taxă: 1.5 RON/h"
		parcariBinding.title.text = "POI: Parcare Nespălata"
		parcariBinding.content.text = description
		parcariBinding.pinIcon.setOnClickListener { v: View? ->
			parcariBinding.parkingDescription.isVisible = ! parcariBinding.parkingDescription.isVisible
		}
		return parcariBinding.root
	}

	companion object {
		private const val TAG = "FrgTb02Map"
	}
}