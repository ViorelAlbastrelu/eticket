package com.faciee.cti.valbastrelu.eticket.room.entities

import androidx.room.ColumnInfo
import androidx.room.ColumnInfo.INTEGER
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.faciee.cti.valbastrelu.eticket.room.converter.DateConverter
import com.faciee.cti.valbastrelu.eticket.room.converter.TransportTypeConverter
import com.faciee.cti.valbastrelu.eticket.ui.common.TransportType
import java.text.SimpleDateFormat
import java.util.*

@Entity
class Route(
		@field:PrimaryKey val number: Int,
		@field:ColumnInfo(index = true) val name: String,
		@field:TypeConverters(DateConverter::class) val startTime: Date?,
		@field:TypeConverters(DateConverter::class) val endTime: Date?,
		@field:ColumnInfo(typeAffinity = INTEGER, defaultValue = "0") val weekOffset: Int,
		@field:ColumnInfo(typeAffinity = INTEGER, defaultValue = "0") val weekendOffset: Int,
		@field:ColumnInfo val streets: List<String>,
		@field:ColumnInfo val stationsIds: List<Int>?,
		@field:TypeConverters(TransportTypeConverter::class) val transportType: TransportType) {

	val timeFormatted: String
		get() = SimpleDateFormat("HH:mm", Locale(Locale.getDefault().language)).format(startTime)

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other == null || javaClass != other.javaClass) return false
		val route = other as Route
		return number == route.number &&
				transportType == route.transportType
	}

	override fun hashCode(): Int {
		return Objects.hash(number, transportType)
	}

}