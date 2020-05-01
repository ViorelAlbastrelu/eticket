package com.faciee.cti.valbastrelu.eticket.room.entities

import androidx.room.ColumnInfo
import androidx.room.ColumnInfo.INTEGER
import androidx.room.ColumnInfo.TEXT
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
class Station(
		@PrimaryKey(autoGenerate = true) val id: Int,
		@field:ColumnInfo(typeAffinity = TEXT) val name: String,
		@field:ColumnInfo(typeAffinity = INTEGER, defaultValue = "0") val offset : Int,
		@field:ColumnInfo(typeAffinity = INTEGER, defaultValue = "0") val routeNumber: Int
) {

	override fun toString(): String {
		return "Station {id=$id, name='$name', routeNumber=$routeNumber}"
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other == null || javaClass != other.javaClass) return false
		val station = other as Station
		return id == station.id &&
				routeNumber == station.routeNumber &&
				name == station.name &&
				offset == station.offset
	}

	override fun hashCode(): Int {
		return Objects.hash(id, name, routeNumber)
	}
}