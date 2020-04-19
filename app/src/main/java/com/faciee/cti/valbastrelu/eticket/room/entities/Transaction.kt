package com.faciee.cti.valbastrelu.eticket.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.faciee.cti.valbastrelu.eticket.room.converter.DateConverter
import com.faciee.cti.valbastrelu.eticket.room.converter.TransportTypeConverter
import com.faciee.cti.valbastrelu.eticket.ui.common.TransportType
import java.math.BigDecimal
import java.util.*

@Entity
class Transaction(
		@field:PrimaryKey var id: Long,
		@field:TypeConverters(DateConverter::class) var date: Date,
		@field:TypeConverters(TransportTypeConverter::class) var transportType: TransportType,
		@field:ColumnInfo var routeNumber: Int,
		@field:ColumnInfo var amount: BigDecimal)