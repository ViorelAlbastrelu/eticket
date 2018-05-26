package com.faciee.cti.valbastrelu.eticket.util.room;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DateConverter {

	@TypeConverter
	public Date fromTimeStamp(long value){
		return (value == 0 ? null : new Date(value));
	}
	
	@TypeConverter
	public Long dateToTimeStamp(Date date){
		if (date == null) return null;
		else return date.getTime();
	}
}
