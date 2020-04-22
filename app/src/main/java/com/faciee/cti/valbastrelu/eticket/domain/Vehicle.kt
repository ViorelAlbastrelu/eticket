package com.faciee.cti.valbastrelu.eticket.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Vehicle(
		@SerializedName("traseu")
		@Expose var traseu: Int? = null,
		@SerializedName("strazi")
		@Expose var streets: List<TransurbWaypoint>? = null,
		@SerializedName("statii")
		@Expose var stations: List<TransurbWaypoint>? = null
)