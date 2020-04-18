package com.faciee.cti.valbastrelu.eticket.database;

import androidx.annotation.NonNull;
import androidx.room.Room;
import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.faciee.cti.valbastrelu.eticket.room.entities.Ticket;
import com.faciee.cti.valbastrelu.eticket.room.dao.TicketDao;
import com.faciee.cti.valbastrelu.eticket.room.EtkRoomDB;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class TicketDaoTest {
	
	private TicketDao ticketDao;
	private EtkRoomDB eTkRoom;
	
	@Before
	public void createDb() {
		Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
		eTkRoom = Room.inMemoryDatabaseBuilder(context, EtkRoomDB.class)
				.allowMainThreadQueries()
				.build();
		ticketDao = eTkRoom.biletDao();
	}
	
	@After
	public void closeDb(){
		eTkRoom.close();
	}
	
	@Test
	public void checkIfTableIsEmpty() throws InterruptedException {
		List<Ticket> allBilete = LiveDataTestUtil.getValue(ticketDao.getAllTicketsLiveData());
		Assert.assertEquals(0, allBilete.size());
	}
	
	@Test
	public void testInsertInTable() throws InterruptedException {
		Ticket ticket = getTicket(7, true);
		ticketDao.insertTicket(ticket);
		List<Ticket> allBilete = LiveDataTestUtil.getValue(ticketDao.getAllTicketsLiveData());
		Assert.assertEquals(7, allBilete.get(0).getRouteNumber());
	}

	@NonNull
	private Ticket getTicket(int routeNumber, boolean active) {
		return new Ticket(0, routeNumber, active, 1, 1, Calendar.getInstance().getTime());
	}

	@Test
	public void testInstertedObject() throws InterruptedException{
		Ticket ticket = getTicket(7, true);
		ticketDao.insertTicket(ticket);
		List<Ticket> allBilete = LiveDataTestUtil.getValue(ticketDao.getAllTicketsLiveData());
		Assert.assertEquals(ticket, allBilete.get(0));
	
	}
	
	@Test
	public void testSecondInsert() throws InterruptedException {
		Ticket ticket1 = getTicket(10, true);
		ticketDao.insertTickets(ticket1);
		Ticket ticket2 = getTicket(20, false);
		ticketDao.insertTickets(ticket2);
		List<Ticket> allBilete = LiveDataTestUtil.getValue(ticketDao.getAllTicketsLiveData());
		Assert.assertEquals(ticket2, allBilete.get(1));
	}
	
	@Test
	public void testInsertMultiple() throws InterruptedException {
		Ticket ticket1 = getTicket(10, true);
		Thread.sleep(100); //Id-ul este timestamp generat de System.currentTimeMillis()
		Ticket ticket2 = getTicket(20, false);
		ticketDao.insertTickets(ticket1, ticket2);
		List<Ticket> allBilete = LiveDataTestUtil.getValue(ticketDao.getAllTicketsLiveData());
		Assert.assertEquals(ticket2, allBilete.get(1));
	}
	
	@Test
	public void testDeleteBilet() throws InterruptedException {
		Ticket ticket = getTicket(1, true);
		ticketDao.insertTickets(ticket);
		ticketDao.deleteTickets(ticket);
		List<Ticket> allBilete = LiveDataTestUtil.getValue(ticketDao.getAllTicketsLiveData());
		Assert.assertEquals(0, allBilete.size());
	}
	
	@Test
	public void testDeleteAllBilet() throws InterruptedException {
		Ticket ticket1 = getTicket(1, true);
		Thread.sleep(100);
		Ticket ticket2 = getTicket(2, false);
		ticketDao.insertTickets(ticket1, ticket2);
		ticketDao.deleteAll();
		List<Ticket> allBilete = LiveDataTestUtil.getValue(ticketDao.getAllTicketsLiveData());
		Assert.assertEquals(0, allBilete.size());
	}
	
	@Test
	public void testUpdateBilet() throws InterruptedException {
		Ticket ticket = getTicket(102, true);
		ticketDao.insertTickets(ticket);
		ticket.setRouteNumber(104);
		ticketDao.updateTickets(ticket);
		List<Ticket> allBilete = LiveDataTestUtil.getValue(ticketDao.getAllTicketsLiveData());
		Assert.assertEquals(ticket, allBilete.get(0));
	}
}
