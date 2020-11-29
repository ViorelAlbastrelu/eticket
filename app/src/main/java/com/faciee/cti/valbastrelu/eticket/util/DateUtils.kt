package com.faciee.cti.valbastrelu.eticket.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

	fun formattedTime(time: Date): String =
			SimpleDateFormat("HH:mm", Locale(Locale.getDefault().language)).format(time)

	fun formattedTime(time: String) : Date =
			SimpleDateFormat("HH:mm", Locale(Locale.getDefault().language)).parse(time) ?: Calendar.getInstance().time
}
