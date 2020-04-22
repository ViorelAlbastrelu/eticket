package com.faciee.cti.valbastrelu.eticket.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.faciee.cti.valbastrelu.eticket.room.converter.DateConverter
import com.faciee.cti.valbastrelu.eticket.room.converter.PriceConverter
import com.faciee.cti.valbastrelu.eticket.room.converter.TransactionTypeConverter
import com.faciee.cti.valbastrelu.eticket.room.converter.TransportTypeConverter
import com.faciee.cti.valbastrelu.eticket.room.dao.StationDao
import com.faciee.cti.valbastrelu.eticket.room.dao.TicketDao
import com.faciee.cti.valbastrelu.eticket.room.dao.TicketParkingDao
import com.faciee.cti.valbastrelu.eticket.room.dao.TransactionsDao
import com.faciee.cti.valbastrelu.eticket.room.entities.Station
import com.faciee.cti.valbastrelu.eticket.room.entities.Ticket
import com.faciee.cti.valbastrelu.eticket.room.entities.TicketParking
import com.faciee.cti.valbastrelu.eticket.room.entities.Transaction

@Database(entities = [Ticket::class, TicketParking::class, Transaction::class, Station::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, TransportTypeConverter::class, PriceConverter::class, TransactionTypeConverter::class)
abstract class EtkRoomDB : RoomDatabase() {
	abstract fun ticketDao(): TicketDao
	abstract fun ticketParkingDao(): TicketParkingDao
	abstract fun transactionsDao(): TransactionsDao
	abstract fun stationDao(): StationDao

	companion object : SingletonHolder<EtkRoomDB, Context>({
		Room.databaseBuilder(it.applicationContext,
				EtkRoomDB::class.java, "word_database")
				.fallbackToDestructiveMigration()
				.build()
	})
}

open class SingletonHolder<T, A>(creator: (A) -> T) {
	private var creator: ((A) -> T)? = creator
	@Volatile
	private var instance: T? = null

	fun getInstance(arg: A): T {
		val i = instance
		if (i != null) {
			return i
		}

		return synchronized(this) {
			val i2 = instance
			if (i2 != null) {
				i2
			} else {
				val created = creator !!(arg)
				instance = created
				creator = null
				created
			}
		}
	}
}