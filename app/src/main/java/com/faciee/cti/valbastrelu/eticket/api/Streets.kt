package com.faciee.cti.valbastrelu.eticket.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Streets(
		@SerializedName("tur")
		@Expose
		val tur: List<String>?,
		@SerializedName("retur")
		@Expose
		val retur: List<String>?
)