package com.faciee.cti.valbastrelu.eticket.room.converter

import androidx.room.TypeConverter
import com.faciee.cti.valbastrelu.eticket.ui.common.TransactionType

class TransactionTypeConverter {
	@TypeConverter
	fun toTransactionType(ordinal: Int): TransactionType {
		return TransactionType.values()[ordinal]
	}

	@TypeConverter
	fun toOrdinal(transactionType: TransactionType): Int {
		return transactionType.ordinal
	}
}