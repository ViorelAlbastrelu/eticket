package com.faciee.cti.valbastrelu.eticket.room.converter

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
	@TypeConverter
	fun fromTimeStamp(value: Long): Date? {
		return if (value == 0L) null else Date(value)
	}

	@TypeConverter
	fun dateToTimeStamp(date: Date?): Long? {
		return date?.time
	}
}