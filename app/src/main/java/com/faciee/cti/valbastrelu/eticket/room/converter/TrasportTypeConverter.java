package com.faciee.cti.valbastrelu.eticket.room.converter;

import androidx.room.TypeConverter;

import com.faciee.cti.valbastrelu.eticket.ui.common.TransportType;

public class TrasportTypeConverter {
	
	@TypeConverter
	public static TransportType toTransportType(int ordinal) {
		return TransportType.values()[ordinal];
	}
	
	@TypeConverter
	public static Integer toOrdinal(TransportType transportType) {
		return transportType.ordinal();
	}
}
