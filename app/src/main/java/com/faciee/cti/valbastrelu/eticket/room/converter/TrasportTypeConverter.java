package com.faciee.cti.valbastrelu.eticket.room.converter;

import android.arch.persistence.room.TypeConverter;

import com.faciee.cti.valbastrelu.eticket.ui.bus.model.TransportType;

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
