package com.faciee.cti.valbastrelu.eticket.database;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.faciee.cti.valbastrelu.eticket.ui.bus.model.Statie;
import com.faciee.cti.valbastrelu.eticket.room.EtkRoomDB;
import com.faciee.cti.valbastrelu.eticket.room.StatieDao;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class StatieDaoTest {
	
	private StatieDao statieDao;
	private EtkRoomDB etkRoomDB;
	
	@Before
	public void createDb(){
		Context context = InstrumentationRegistry.getTargetContext();
		etkRoomDB = Room.inMemoryDatabaseBuilder(context, EtkRoomDB.class)
				.allowMainThreadQueries()
				.build();
		statieDao = etkRoomDB.statieDao();
	}
	
	@After
	public void closeDb(){
		etkRoomDB.close();
	}
	
	@Test
	public void checkIfTableIsEmpty() throws InterruptedException {
		List<Statie> allStatii = LiveDataTestUtil.getValue(statieDao.getAllStatii());
		Assert.assertEquals(0, allStatii.size());
	}
	
	@Test
	public void testInsertInTable() throws InterruptedException {
		Statie statie = new Statie(7, "Test", "Tur");
		statieDao.insertStatii(statie);
		List<Statie> allStatii = LiveDataTestUtil.getValue(statieDao.getAllStatii());
		Assert.assertEquals(statie.getNumeStatie(), allStatii.get(0).getNumeStatie());
	}
	
	@Test
	public void testInsertedObject() throws InterruptedException {
		Statie statie = new Statie(7, "Test", "Tur");
		statieDao.insertStatii(statie);
		List<Statie> allStatii = LiveDataTestUtil.getValue(statieDao.getAllStatii());
		Assert.assertEquals(statie, allStatii.get(0));
	}
	
	@Test
	public void testSecondInsert() throws InterruptedException {
		Statie statie = new Statie(7, "Statie1", "Tur");
		statieDao.insertStatii(statie);
		Statie statie2 = new Statie(7, "Statie2", "Tur");
		statieDao.insertStatii(statie2);
		List<Statie> allStatii = LiveDataTestUtil.getValue(statieDao.getAllStatii());
		Assert.assertEquals(statie2, allStatii.get(1));
	}
	
	@Test
	public void testDeleteBilet() throws InterruptedException {
		Statie statie = new Statie(7, "Statie1", "Tur");
		statieDao.insertStatii(statie);
		statieDao.deleteStatii(statie);
		List<Statie> allStatii = LiveDataTestUtil.getValue(statieDao.getAllStatii());
		Assert.assertEquals(0, allStatii.size());
	}
	
	@Test
	public void testInsertAllBilet() throws InterruptedException {
		Statie statie = new Statie(7, "Statie1", "Tur");
		Statie statie2 = new Statie(7, "Statie2", "Tur");
		statieDao.insertStatii(statie, statie2);
		List<Statie> allStatii = LiveDataTestUtil.getValue(statieDao.getAllStatii());
		Assert.assertEquals(2, allStatii.size());
	}
	
	@Test
	public void testDeleteAllBilet() throws InterruptedException {
		Statie statie = new Statie(7, "Statie1", "Tur");
		Statie statie2 = new Statie(7, "Statie2", "Tur");
		statieDao.insertStatii(statie, statie2);
		statieDao.deleteAllStatii();
		List<Statie> allStatii = LiveDataTestUtil.getValue(statieDao.getAllStatii());
		Assert.assertEquals(0, allStatii.size());
	}
	
	@Test
	public void testUpdateBilet() throws InterruptedException {
		Statie statie = new Statie(7, "Statie1", "Tur");
		statieDao.insertStatii(statie);
		statie.setNumeStatie("TestNume");
		statieDao.updateStatii(statie);
		List<Statie> allStatii = LiveDataTestUtil.getValue(statieDao.getAllStatii());
		Assert.assertEquals("TestNume", allStatii.get(0).getNumeStatie());
	}
	
	@Test
	public void testUpdateSpecificBilet() throws InterruptedException {
		Statie statie1 = new Statie(7, "Statie1", "Tur");
		Statie statie2 = new Statie(7, "Statie2", "Tur");
		Statie statie3 = new Statie(7, "Statie3", "Tur");
		statieDao.insertStatii(statie1, statie2, statie3);
		statie2.setNumeStatie("TestNume");
		statieDao.updateStatii(statie2);
		List<Statie> allStatii = LiveDataTestUtil.getValue(statieDao.getAllStatii());
		Assert.assertEquals("TestNume", allStatii.get(1).getNumeStatie());
	}
	
}
