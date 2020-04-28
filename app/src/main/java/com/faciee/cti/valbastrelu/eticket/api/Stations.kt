package com.faciee.cti.valbastrelu.eticket.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Stations(
		@SerializedName("tur")
		@Expose
		val tur: List<Station>?,
		@SerializedName("retur")
		@Expose
		val retur: List<Station>?
)