package com.faciee.cti.valbastrelu.eticket.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Station(
		@SerializedName("name")
		@Expose
		val name: String,
		@SerializedName("offset")
		@Expose
		val offset: Int
)