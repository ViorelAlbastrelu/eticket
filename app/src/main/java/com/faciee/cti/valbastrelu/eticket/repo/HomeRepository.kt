package com.faciee.cti.valbastrelu.eticket.repo

import com.faciee.cti.valbastrelu.eticket.room.EtkRoomDB
import com.faciee.cti.valbastrelu.eticket.room.dao.TicketDao
import com.faciee.cti.valbastrelu.eticket.room.dao.TicketParkingDao
import com.faciee.cti.valbastrelu.eticket.util.DummyData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(database: EtkRoomDB) {

	private val ticketDao: TicketDao = database.ticketDao()
	private val parkingTicketDao: TicketParkingDao = database.ticketParkingDao()

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
			localList.add(ticketDao.recentTicket)
			localList.add(parkingTicketDao.recentTicket)
		}
		return localList
	}

	suspend fun clearDB() {
		ticketDao.deleteAll()
	}
}