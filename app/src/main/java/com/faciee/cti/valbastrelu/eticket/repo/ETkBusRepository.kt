package com.faciee.cti.valbastrelu.eticket.repo

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.faciee.cti.valbastrelu.eticket.room.EtkRoomDB
import com.faciee.cti.valbastrelu.eticket.room.dao.StationDao
import com.faciee.cti.valbastrelu.eticket.room.dao.TicketDao
import com.faciee.cti.valbastrelu.eticket.room.dao.TransactionsDao
import com.faciee.cti.valbastrelu.eticket.room.entities.Route
import com.faciee.cti.valbastrelu.eticket.room.entities.Ticket
import com.faciee.cti.valbastrelu.eticket.room.entities.Transaction
import com.faciee.cti.valbastrelu.eticket.ui.common.TransportType
import com.faciee.cti.valbastrelu.eticket.util.DummyData
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import java.util.*

class ETkBusRepository(roomDB: EtkRoomDB) {
	//Dao
	private val ticketDao: TicketDao = roomDB.ticketDao()
	private val stationDao: StationDao = roomDB.stationDao()
	private val transactionsDao: TransactionsDao = roomDB.transactionsDao()

	//Livedata
	private val bileteLiveData: MutableLiveData<List<Ticket>>? = null
	private var traseeLiveData: MutableLiveData<List<Route>>? = null
	private val tranzactiiLiveData: MutableLiveData<List<Transaction>>? = null
	private var statiiLiveData: StatiiLiveData? = null
	val bilete: LiveData<List<Ticket?>?>?
		get() = ticketDao.allTicketsLiveData

	//		if (tranzactiiLiveData == null){
//			tranzactiiLiveData = new MutableLiveData<>();
//			loadIstorice();
//		}
//		return tranzactiiLiveData;
	val liveDataTranzactii: LiveData<List<Transaction?>?>?
		get() = transactionsDao.allTransactionsLiveData
	//		if (tranzactiiLiveData == null){
//			tranzactiiLiveData = new MutableLiveData<>();
//			loadIstorice();
//		}
//		return tranzactiiLiveData;

	val liveDataTrasee: LiveData<List<Route>>
		get() {
			if (traseeLiveData == null) {
				traseeLiveData = MutableLiveData()
				traseeLiveData !!.value = DummyData.loadTrasee()
			}
			return traseeLiveData !!
		}

	fun getLiveDataStatii(nrTraseu: Int): LiveData<List<String>> {
		statiiLiveData = StatiiLiveData(nrTraseu)
		return statiiLiveData !!.numeStatii
	}

	//	LiveData<List<Statie>> getStatieForTraseu(int nrTraseu){
//		return statieDao.getStatiiForTraseu(nrTraseu);
//	}
	fun insertBilet(ticket: Ticket?) {
		InsertBiletAsync(ticketDao, transactionsDao).execute(ticket)
	}

	private class InsertBiletAsync(private val ticketDao: TicketDao, private val transactionsDao: TransactionsDao) :
			AsyncTask<Ticket?, Void?, Void?>() {

		override fun doInBackground(vararg arrayOfTickets: Ticket?): Void? {
			arrayOfTickets.forEach { ticket ->
				ticket?.let {
					ticketDao.updateTicketActiveStatus(it.id, false)
					ticketDao.insertTickets(it)
					val price = it.price.multiply(BigDecimal.TEN, MathContext.DECIMAL32).setScale(2, RoundingMode.HALF_EVEN)
					transactionsDao.insertTransactions(Transaction(it.id, Calendar.getInstance().time,
							TransportType.BUS, it.routeNumber, price))
				}

			}
			return null
		}

//		protected override fun doInBackground(vararg bilete: Ticket): Void? {

//		}

	}

	companion object {
		private var INSTANCE: ETkBusRepository? = null
		fun getInstance(database: EtkRoomDB): ETkBusRepository? {
			if (INSTANCE == null) {
				synchronized(ETkBusRepository::class.java) {
					if (INSTANCE == null) {
						INSTANCE = ETkBusRepository(database)
					}
				}
			}
			return INSTANCE
		}
	}
}