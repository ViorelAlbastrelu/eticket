package com.faciee.cti.valbastrelu.eticket.ui.common

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.faciee.cti.valbastrelu.eticket.R

enum class TransportType(val key: String, @param:StringRes val typeName: Int, @param:DrawableRes val transportTypeIcon: Int) {
	BUS("bus", R.string.transport_type_bus, R.drawable.ic_bus),
	TBUS("tbus", R.string.transport_type_troleybus, R.drawable.ic_tbus),
	TRAM("tram", R.string.transport_type_tramway, R.drawable.ic_tram),
	PARKING("auto", R.string.transport_type_parking, R.drawable.ic_auto),
	NOTAVAILABLE("na", R.string.transport_type_not_available, R.drawable.ic_auto);

	companion object {
		fun getTansportTypeByKey(key: String): TransportType {
			return try {
				values().first { it.key == key }
			} catch (e: NoSuchElementException) {
				NOTAVAILABLE
			}
		}
	}
}