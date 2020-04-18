package com.faciee.cti.valbastrelu.eticket.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.faciee.cti.valbastrelu.eticket.room.dao.TicketDao
import com.faciee.cti.valbastrelu.eticket.room.dao.TicketParkingDao
import com.faciee.cti.valbastrelu.eticket.room.dao.StationDao
import com.faciee.cti.valbastrelu.eticket.room.dao.TransactionsDao
import com.faciee.cti.valbastrelu.eticket.room.entities.Station
import com.faciee.cti.valbastrelu.eticket.room.entities.Ticket
import com.faciee.cti.valbastrelu.eticket.room.entities.TicketParking
import com.faciee.cti.valbastrelu.eticket.room.entities.Transaction

@Database(entities = [Ticket::class, TicketParking::class, Transaction::class, Station::class], version = 0, exportSchema = false)
abstract class EtkRoomDB : RoomDatabase() {
	abstract fun biletDao(): TicketDao
	abstract fun biletpDao(): TicketParkingDao
	abstract fun tranzactieDao(): TransactionsDao
	abstract fun statieDao(): StationDao

	companion object {
		private var INSTANCE: EtkRoomDB? = null

		/**
		 * Wipes and rebuilds instead of migrating.
		 */
		fun getDatabase(context: Context): EtkRoomDB? {
			if (INSTANCE == null) {
				synchronized(EtkRoomDB::class.java) {
					if (INSTANCE == null) {
						INSTANCE = Room.databaseBuilder(context.applicationContext,
								EtkRoomDB::class.java, "word_database")
								.fallbackToDestructiveMigration()
								.build()
					}
				}
			}
			return INSTANCE
		}
	}
}