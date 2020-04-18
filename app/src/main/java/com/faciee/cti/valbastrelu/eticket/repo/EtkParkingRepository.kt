package com.faciee.cti.valbastrelu.eticket.repo

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.faciee.cti.valbastrelu.eticket.room.EtkRoomDB
import com.faciee.cti.valbastrelu.eticket.room.dao.TicketParkingDao
import com.faciee.cti.valbastrelu.eticket.room.dao.TransactionsDao
import com.faciee.cti.valbastrelu.eticket.room.entities.TicketParking
import com.faciee.cti.valbastrelu.eticket.room.entities.Transaction
import com.faciee.cti.valbastrelu.eticket.ui.common.TransportType
import java.math.BigDecimal

class EtkParkingRepository(db: EtkRoomDB) {
	//Dao
	private val ticketParkingDao: TicketParkingDao = db.ticketParkingDao()
	private val transactionsDao: TransactionsDao = db.transactionsDao()
	val bileteParcare: LiveData<List<TicketParking?>?>?
		get() = ticketParkingDao.allTicketsLiveData

	val liveDataTranzactii: LiveData<List<Transaction?>?>?
		get() = transactionsDao.allTransactionsLiveData

	fun insertBilet(bilet: TicketParking?) {
		InsertBiletAsync(ticketParkingDao, transactionsDao).execute(bilet)
	}

	private class InsertBiletAsync(private val ticketParkingDao: TicketParkingDao, private val transactionsDao: TransactionsDao) : AsyncTask<TicketParking?, Void?, Void?>() {

		override fun doInBackground(vararg bilete: TicketParking?): Void? {
			bilete.forEach { ticketParking ->
				ticketParking?.let {
					ticketParkingDao.updateTicketActiveStatus(it.id, false)
					ticketParkingDao.insertTickets(*bilete)
					transactionsDao.insertTransactions(Transaction(it.id, it.date,
							TransportType.PARKING, 0, it.price.multiply(BigDecimal(- 1.0))))
				}
			}
			return null
		}

	}

	companion object {
		private var INSTANCE: EtkParkingRepository? = null
		fun getInstance(database: EtkRoomDB): EtkParkingRepository? {
			if (INSTANCE == null) {
				synchronized(EtkParkingRepository::class.java) {
					if (INSTANCE == null) {
						INSTANCE = EtkParkingRepository(database)
					}
				}
			}
			return INSTANCE
		}
	}
}