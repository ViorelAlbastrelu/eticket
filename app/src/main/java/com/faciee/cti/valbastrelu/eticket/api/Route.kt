package com.faciee.cti.valbastrelu.eticket.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Route(
		@SerializedName("routeNumber")
		@Expose
		val routeNumber: Int,
		@SerializedName("start")
		@Expose
		val start: String,
		@SerializedName("end")
		@Expose
		val end: String,
		@SerializedName("weekdays")
		@Expose
		val weekdays: Int,
		@SerializedName("weekend")
		@Expose
		val weekend: Int,
		@SerializedName("streets")
		@Expose
		val streets: List<Streets>,
		@SerializedName("stations")
		@Expose
		val stations: Stations
)
