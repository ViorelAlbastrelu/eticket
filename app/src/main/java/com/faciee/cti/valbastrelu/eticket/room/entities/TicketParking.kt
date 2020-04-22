package com.faciee.cti.valbastrelu.eticket.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.faciee.cti.valbastrelu.eticket.room.converter.DateConverter
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

@Entity
class TicketParking(
		@PrimaryKey var id: Long = System.currentTimeMillis(),
		@ColumnInfo var location: String,
		@ColumnInfo var active: Boolean = false,
		@ColumnInfo var price: BigDecimal = BigDecimal.ZERO,
		@ColumnInfo var alertOn: Boolean = false,
		@TypeConverters(DateConverter::class) var date: Date = Calendar.getInstance().time
) {
	constructor(location: String, active: Boolean, price: BigDecimal, alertOn: Boolean) :
			this (id = System.currentTimeMillis(), location = location, active = active, price = price, alertOn = alertOn, date = Calendar.getInstance().time)

	val hourMinute: String
		get() = SimpleDateFormat("HH:mm").format(date)

	val dayMonthYear: String
		get() = SimpleDateFormat("dd-MMM-yyyy").format(date)

	override fun toString(): String {
		return "Parking ticket { id=$id, location='$location, active=$active, price=$price, alertOn=$alertOn, date=$date}"
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other == null || javaClass != other.javaClass) return false
		val ticket = other as TicketParking
		return id == ticket.id &&
				active == ticket.active &&
				price == ticket.price &&
				location == ticket.location &&
				date == ticket.date
	}

	override fun hashCode(): Int {
		return Objects.hash(id, location, active, price, alertOn, date)
	}
}