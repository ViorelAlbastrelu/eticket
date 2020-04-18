package com.faciee.cti.valbastrelu.eticket.room.entities

import android.annotation.SuppressLint
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.faciee.cti.valbastrelu.eticket.room.converter.DateConverter
import com.faciee.cti.valbastrelu.eticket.room.converter.TrasportTypeConverter
import com.faciee.cti.valbastrelu.eticket.ui.common.TransportType
import java.text.SimpleDateFormat
import java.util.*

@Entity
class Route(
		@field:PrimaryKey var number: Int,
		@field:TypeConverters(DateConverter::class) var date: Date?,
		@field:TypeConverters(TrasportTypeConverter::class) var transportType: TransportType) {

	val timeFormatted: String
		get() = SimpleDateFormat("HH:mm", Locale(Locale.getDefault().language)).format(date)

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