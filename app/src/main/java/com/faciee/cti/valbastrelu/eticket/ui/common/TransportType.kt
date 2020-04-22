package com.faciee.cti.valbastrelu.eticket.ui.common

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.faciee.cti.valbastrelu.eticket.R

enum class TransportType(@param:StringRes val typeName: Int, @param:DrawableRes val transportTypeIcon: Int) {
	BUS(R.string.transport_type_bus, R.drawable.ic_bus),
	TBUS(R.string.transport_type_troleybus, R.drawable.ic_tbus),
	TRAM(R.string.transport_type_tramway, R.drawable.ic_tram),
	PARKING(R.string.transport_type_parking, R.drawable.ic_auto),
	NOTAVAILABLE(R.string.transport_type_not_available, R.drawable.ic_auto);
}