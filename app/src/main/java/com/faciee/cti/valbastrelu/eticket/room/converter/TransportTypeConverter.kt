package com.faciee.cti.valbastrelu.eticket.room.converter

import androidx.room.TypeConverter
import com.faciee.cti.valbastrelu.eticket.ui.common.TransportType

object TrasportTypeConverter {
	@TypeConverter
	fun toTransportType(ordinal: Int): TransportType {
		return TransportType.values()[ordinal]
	}

	@TypeConverter
	fun toOrdinal(transportType: TransportType): Int {
		return transportType.ordinal
	}
}