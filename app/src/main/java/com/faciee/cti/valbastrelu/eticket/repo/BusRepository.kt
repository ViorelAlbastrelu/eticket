package com.faciee.cti.valbastrelu.eticket.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.faciee.cti.valbastrelu.eticket.base.SingletonHolder
import com.faciee.cti.valbastrelu.eticket.room.EtkRoomDB
import com.faciee.cti.valbastrelu.eticket.room.dao.RoutesDao
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

class BusRepository(roomDB: EtkRoomDB) {
	//Dao
	private val ticketDao: TicketDao = roomDB.ticketDao()
	private val stationDao: StationDao = roomDB.stationDao()
	private val routesDao: RoutesDao = roomDB.routesDao()
	private val transactionsDao: TransactionsDao = roomDB.transactionsDao()

	//Livedata
	private val bileteLiveData: MutableLiveData<List<Ticket>>? = null
	private val tranzactiiLiveData: MutableLiveData<List<Transaction>>? = null

	val ticketsLiveData: LiveData<List<Ticket>>
		get() = ticketDao.allTicketsLiveData

	val liveDataTranzactii: LiveData<List<Transaction>>
		get() = transactionsDao.allTransactionsLiveData

	suspend fun getRoutes(type: TransportType): List<Route> {
		return routesDao.getRoutesForType(type)
	}

	fun getLiveDataStatii(nrTraseu: Int): LiveData<List<String>> {
//		statiiLiveData = StatiiLiveData(nrTraseu)
//		return statiiLiveData !!.numeStatii
		return MutableLiveData(listOf())
	}

	//	LiveData<List<Statie>> getStatieForTraseu(int nrTraseu){
//		return statieDao.getStatiiForTraseu(nrTraseu);
//	}
	suspend fun insertTicketInDatabase(ticket: Ticket) {
		ticketDao.updateTicketActiveStatus(ticket.id, false) //TODO do I really need this?
		ticketDao.insertTicket(ticket)
		val price = ticket.price.multiply(BigDecimal.TEN, MathContext.DECIMAL32).setScale(2, RoundingMode.HALF_EVEN)
		transactionsDao.insertTransactions(Transaction(ticket.id, Calendar.getInstance().time,
				TransportType.BUS, ticket.routeNumber, price))
	}

	companion object : SingletonHolder<BusRepository, EtkRoomDB>({ BusRepository(it) })
}