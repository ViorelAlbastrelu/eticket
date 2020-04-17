package com.faciee.cti.valbastrelu.eticket.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TransurbWaypoint(
		@SerializedName("tur")
		@Expose var tur: List<String>? = null,
		@SerializedName("retur")
		@Expose var retur: List<String>? = null
)