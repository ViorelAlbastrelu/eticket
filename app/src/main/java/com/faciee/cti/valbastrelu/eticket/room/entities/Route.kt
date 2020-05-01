package com.faciee.cti.valbastrelu.eticket.room.entities

import androidx.room.ColumnInfo
import androidx.room.ColumnInfo.INTEGER
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.faciee.cti.valbastrelu.eticket.room.converter.DateConverter
import com.faciee.cti.valbastrelu.eticket.room.converter.TransportTypeConverter
import com.faciee.cti.valbastrelu.eticket.ui.common.TransportType
import com.faciee.cti.valbastrelu.eticket.util.DateUtils
import java.text.SimpleDateFormat
import java.util.*

@Entity
class Route(
		@field:PrimaryKey var number: Int,
		@field:TypeConverters(DateConverter::class) var startTime: Date?,
		@field:TypeConverters(DateConverter::class) var endTime: Date?,
		@field:ColumnInfo(typeAffinity = INTEGER, defaultValue = "0") var weekOffset: Int,
		@field:ColumnInfo(typeAffinity = INTEGER, defaultValue = "0") var weekendOffset: Int,
		@field:Ignore var streets: List<String>?,
		@field:Ignore var stationsIds: List<Int>?,
		@field:TypeConverters(TransportTypeConverter::class) var transportType: TransportType) {

	constructor(number: Int, startTime: Date?, endTime: Date?, weekOffset: Int, weekendOffset: Int, transportType: TransportType) : this(
			number, startTime, endTime, weekOffset, weekendOffset, null, null, transportType
	)

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