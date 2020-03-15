package com.faciee.cti.valbastrelu.eticket.database;

import androidx.room.Room;
import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.faciee.cti.valbastrelu.eticket.room.entities.Bilet;
import com.faciee.cti.valbastrelu.eticket.room.dao.BiletDao;
import com.faciee.cti.valbastrelu.eticket.room.EtkRoomDB;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class BiletDaoTest {
	
	private BiletDao biletDao;
	private EtkRoomDB eTkRoom;
	
	@Before
	public void createDb() {
		Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
		eTkRoom = Room.inMemoryDatabaseBuilder(context, EtkRoomDB.class)
				.allowMainThreadQueries()
				.build();
		biletDao = eTkRoom.biletDao();
	}
	
	@After
	public void closeDb(){
		eTkRoom.close();
	}
	
	@Test
	public void checkIfTableIsEmpty() throws InterruptedException {
		List<Bilet> allBilete = LiveDataTestUtil.getValue(biletDao.getAllBilete());
		Assert.assertEquals(0, allBilete.size());
	}
	
	@Test
	public void testInsertInTable() throws InterruptedException {
		Bilet bilet = new Bilet(7, true, 1, 1);
		biletDao.insertOneBilet(bilet);
		List<Bilet> allBilete = LiveDataTestUtil.getValue(biletDao.getAllBilete());
		Assert.assertEquals(7, allBilete.get(0).getTraseu());
	}
	
	@Test
	public void testInstertedObject() throws InterruptedException{
		Bilet bilet = new Bilet(7, true, 1, 1);
		biletDao.insertOneBilet(bilet);
		List<Bilet> allBilete = LiveDataTestUtil.getValue(biletDao.getAllBilete());
		Assert.assertEquals(bilet, allBilete.get(0));
	
	}
	
	@Test
	public void testSecondInsert() throws InterruptedException {
		Bilet bilet1 = new Bilet(10, true, 1, 1);
		biletDao.insertBilete(bilet1);
		Bilet bilet2 = new Bilet(20, false, 1, 1);
		biletDao.insertBilete(bilet2);
		List<Bilet> allBilete = LiveDataTestUtil.getValue(biletDao.getAllBilete());
		Assert.assertEquals(bilet2, allBilete.get(1));
	}
	
	@Test
	public void testInsertMultiple() throws InterruptedException {
		Bilet bilet1 = new Bilet(10, true, 1, 1);
		Thread.sleep(100); //Id-ul este timestamp generat de System.currentTimeMillis()
		Bilet bilet2 = new Bilet(20, false, 1, 1);
		biletDao.insertBilete(bilet1, bilet2);
		List<Bilet> allBilete = LiveDataTestUtil.getValue(biletDao.getAllBilete());
		Assert.assertEquals(bilet2, allBilete.get(1));
	}
	
	@Test
	public void testDeleteBilet() throws InterruptedException {
		Bilet bilet = new Bilet(1, true, 1, 1);
		biletDao.insertBilete(bilet);
		biletDao.deleteBilete(bilet);
		List<Bilet> allBilete = LiveDataTestUtil.getValue(biletDao.getAllBilete());
		Assert.assertEquals(0, allBilete.size());
	}
	
	@Test
	public void testDeleteAllBilet() throws InterruptedException {
		Bilet bilet1 = new Bilet(1, true, 1, 1);
		Thread.sleep(100);
		Bilet bilet2 = new Bilet(2, false, 1, 1);
		biletDao.insertBilete(bilet1, bilet2);
		biletDao.deleteAll();
		List<Bilet> allBilete = LiveDataTestUtil.getValue(biletDao.getAllBilete());
		Assert.assertEquals(0, allBilete.size());
	}
	
	@Test
	public void testUpdateBilet() throws InterruptedException {
		Bilet bilet = new Bilet(102, true, 1, 1);
		biletDao.insertBilete(bilet);
		bilet.setTraseu(104);
		biletDao.updateBilete(bilet);
		List<Bilet> allBilete = LiveDataTestUtil.getValue(biletDao.getAllBilete());
		Assert.assertEquals(bilet, allBilete.get(0));
	}
}
