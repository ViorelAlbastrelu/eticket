package com.faciee.cti.valbastrelu.eticket.database

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.faciee.cti.valbastrelu.eticket.room.EtkRoomDB
import com.faciee.cti.valbastrelu.eticket.room.dao.TicketDao
import com.faciee.cti.valbastrelu.eticket.room.entities.Ticket
import junit.framework.Assert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.math.BigDecimal
import java.util.*

@RunWith(AndroidJUnit4::class)
class TicketDaoTest {
	private lateinit var ticketDao: TicketDao
	private lateinit var eTkRoom: EtkRoomDB
	@Before
	fun createDb() {
		val context = InstrumentationRegistry.getInstrumentation().targetContext
		eTkRoom = Room.inMemoryDatabaseBuilder(context, EtkRoomDB::class.java)
				.allowMainThreadQueries()
				.build()
		ticketDao = eTkRoom.ticketDao()
	}

	@After
	fun closeDb() {
		eTkRoom.close()
	}

	@Test
	@Throws(InterruptedException::class)
	fun checkIfTableIsEmpty() {
		val allBilete = ticketDao.allTicketsLiveData.testableValue()
		Assert.assertEquals(0, allBilete.size)
	}

	@Test
	@Throws(InterruptedException::class)
	fun testInsertInTable() {
		val ticket = getTicket(7, true)
		ticketDao.insertTicket(ticket)
		val allBilete = ticketDao.allTicketsLiveData.testableValue()
		Assert.assertEquals(7, allBilete[0].routeNumber)
	}

	@Test
	@Throws(InterruptedException::class)
	fun testInstertedObject() {
		val ticket = getTicket(7, true)
		ticketDao.insertTicket(ticket)
		val allBilete = ticketDao.allTicketsLiveData.testableValue()
		Assert.assertEquals(ticket, allBilete[0])
	}

	@Test
	@Throws(InterruptedException::class)
	fun testSecondInsert() {
		val ticket1 = getTicket(10, true)
		ticketDao.insertTickets(ticket1)
		val ticket2 = getTicket(20, false)
		ticketDao.insertTickets(ticket2)
		val allBilete = ticketDao.allTicketsLiveData.testableValue()
		Assert.assertEquals(ticket2, allBilete[1])
	}

	@Test
	@Throws(InterruptedException::class)
	fun testInsertMultiple() {
		val ticket1 = getTicket(10, true)
		Thread.sleep(100) //Id-ul este timestamp generat de System.currentTimeMillis()
		val ticket2 = getTicket(20, false)
		ticketDao.insertTickets(ticket1, ticket2)
		val allBilete = ticketDao.allTicketsLiveData.testableValue()
		Assert.assertEquals(ticket2, allBilete[1])
	}

	@Test
	@Throws(InterruptedException::class)
	fun testDeleteBilet() {
		val ticket = getTicket(1, true)
		ticketDao.insertTickets(ticket)
		ticketDao.deleteTickets(ticket)
		val allBilete = ticketDao.allTicketsLiveData.testableValue()
		Assert.assertEquals(0, allBilete.size)
	}

	@Test
	@Throws(InterruptedException::class)
	fun testDeleteAllBilet() {
		val ticket1 = getTicket(1, true)
		Thread.sleep(100)
		val ticket2 = getTicket(2, false)
		ticketDao.insertTickets(ticket1, ticket2)
		ticketDao.deleteAll()
		val allBilete = ticketDao.allTicketsLiveData.testableValue()
		Assert.assertEquals(0, allBilete.size)
	}

	@Test
	@Throws(InterruptedException::class)
	fun testUpdateBilet() {
		val ticket = getTicket(102, true)
		ticketDao.insertTickets(ticket)
		ticket.routeNumber = 104
		ticketDao.updateTickets(ticket)
		val allBilete = ticketDao.allTicketsLiveData.testableValue()
		Assert.assertEquals(ticket, allBilete[0])
	}

	private fun getTicket(routeNumber: Int, active: Boolean): Ticket {
		return Ticket(0, routeNumber, active, 1, BigDecimal.ONE, Calendar.getInstance().time)
	}
}