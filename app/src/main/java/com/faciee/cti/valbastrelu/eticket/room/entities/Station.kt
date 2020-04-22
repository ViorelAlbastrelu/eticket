package com.faciee.cti.valbastrelu.eticket.room.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity
class Station(
		@PrimaryKey
		var id: Int = idGenerator ++,
		var name: String,
		var direction: String,
		var routeNumber: Int
) {
	constructor(name: String, direction: String, routeNumber: Int) :
			this(id = idGenerator ++, name = name, direction = direction, routeNumber = routeNumber)

	override fun toString(): String {
		return "Station {id=$id, name='$name', direction='$direction', routeNumber=$routeNumber}"
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other == null || javaClass != other.javaClass) return false
		val statie = other as Station
		return id == statie.id &&
				routeNumber == statie.routeNumber &&
				name == statie.name &&
				direction == statie.direction
	}

	override fun hashCode(): Int {
		return Objects.hash(id, name, direction, routeNumber)
	}

	companion object {
		@Ignore
		private var idGenerator = 0
	}
}