package com.faciee.cti.valbastrelu.eticket.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Schedule(
		@SerializedName("bus")
		@Expose
		val bus: List<Route>,
		@SerializedName("tbus")
		@Expose
		val tbus: List<Route>,
		@SerializedName("tram")
		@Expose
		val tram: List<Route>,
		@SerializedName("estbus")
		@Expose
		val estbus: List<Route>
)
