package com.faciee.cti.valbastrelu.eticket.repo

import com.faciee.cti.valbastrelu.eticket.base.SingletonHolder
import com.faciee.cti.valbastrelu.eticket.room.EtkRoomDB
import com.faciee.cti.valbastrelu.eticket.room.dao.RoutesDao
import com.faciee.cti.valbastrelu.eticket.room.dao.TicketDao
import com.faciee.cti.valbastrelu.eticket.room.dao.TicketParkingDao
import com.faciee.cti.valbastrelu.eticket.ui.common.TransportType
import com.faciee.cti.valbastrelu.eticket.util.DummyData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(database: EtkRoomDB) {

	private val ticketDao: TicketDao = database.ticketDao()
	private val parkingTicketDao: TicketParkingDao = database.ticketParkingDao()
	private val routesDao: RoutesDao = database.routesDao()

	suspend fun insertTicketInDatabase() {
		withContext(Dispatchers.IO) {
			DummyData.loadBilete().forEach {
				ticketDao.insertTicket(it)
			}
			DummyData.loadParkingTickets().forEach{
				parkingTicketDao.insertTicket(it)
			}
		}
	}

	suspend fun getLatestActiveTickets(): List<Any> {
		val localList = arrayListOf<Any>()
		withContext(Dispatchers.IO){
			ticketDao.recentTicket?.let { localList.add(it) }
			parkingTicketDao.recentTicket?.let { localList.add(it) }
			routesDao.getLatestRoutes().let { localList.addAll(it) }
		}
		return localList
	}

	suspend fun clearDB() {
		ticketDao.deleteAll()
	}

	companion object : SingletonHolder<HomeRepository, EtkRoomDB>({HomeRepository(it)})
}