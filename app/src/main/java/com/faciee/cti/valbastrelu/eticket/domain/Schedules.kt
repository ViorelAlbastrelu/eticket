package com.faciee.cti.valbastrelu.eticket.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Schedules(
		@SerializedName("bus")
		@Expose var buses: List<Vehicle>? = null,
		@SerializedName("tbus")
		@Expose var trolleyBuses: List<Vehicle>? = null,
		@SerializedName("tram")
		@Expose var tramway: List<Vehicle>? = null,
		@SerializedName("estbus")
		@Expose var estivalBuses: List<Vehicle>? = null
)