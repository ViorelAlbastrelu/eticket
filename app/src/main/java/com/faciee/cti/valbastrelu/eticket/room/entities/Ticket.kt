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
data class Ticket(
		@PrimaryKey val id: Long = System.currentTimeMillis(),
		@ColumnInfo var routeNumber: Int,
		@ColumnInfo var active: Boolean = true,
		@ColumnInfo var trips: Int,
		@ColumnInfo var price: BigDecimal,
		@TypeConverters(DateConverter::class) var date: Date = Calendar.getInstance().time
) {
	constructor(routeNumber: Int, active: Boolean, trips: Int, price: BigDecimal):
			this(id = System.currentTimeMillis(), routeNumber = routeNumber, active = active, trips = trips, price = price, date = Calendar.getInstance().time)

	val hourMinute: String
		get() = SimpleDateFormat("HH:mm", Locale(Locale.getDefault().language)).format(date)
	val dayMonthYear: String
		get() = SimpleDateFormat("dd-MMM-yyyy", Locale(Locale.getDefault().language)).format(date)

	override fun toString(): String {
		return "Ticket {id=$id, routeNumber=$routeNumber, isActive=$active, trips=$trips, price=$price, date=$date}"
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other == null || javaClass != other.javaClass) return false
		val bilet = other as Ticket
		return id == bilet.id && routeNumber == bilet.routeNumber && active == bilet.active && trips == bilet.trips && price == bilet.price &&
				date == bilet.date
	}

	override fun hashCode(): Int {
		return Objects.hash(id, routeNumber, active, trips, price, date)
	}
}