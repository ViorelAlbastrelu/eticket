package com.faciee.cti.valbastrelu.eticket.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TransurbSchedule(
		@SerializedName("itinerariu")
		@Expose var schedules: Schedules? = null
)