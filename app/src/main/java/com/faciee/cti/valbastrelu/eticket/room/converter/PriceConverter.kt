package com.faciee.cti.valbastrelu.eticket.room.converter

import androidx.room.TypeConverter
import java.math.BigDecimal

class PriceConverter {
	@TypeConverter
	fun toBigDecimalPrice(value: Double): BigDecimal {
		return BigDecimal(value)
	}

	@TypeConverter
	fun toDoublePrice(value: BigDecimal): Double {
		return value.toDouble()
	}
}