package com.faciee.cti.valbastrelu.eticket.database;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.faciee.cti.valbastrelu.eticket.room.entities.Station;
import com.faciee.cti.valbastrelu.eticket.room.EtkRoomDB;
import com.faciee.cti.valbastrelu.eticket.room.dao.StationDao;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class StationDaoTest {

	private StationDao stationDao;
	private EtkRoomDB etkRoomDB;

	@Before
	public void createDb() {
		Context context = InstrumentationRegistry.getInstrumentation().getContext();
		etkRoomDB = Room.inMemoryDatabaseBuilder(context, EtkRoomDB.class)
				.allowMainThreadQueries()
				.build();
		stationDao = etkRoomDB.stationDao();
	}

	@After
	public void closeDb() {
		etkRoomDB.close();
	}

	@Test
	public void checkIfTableIsEmpty() throws InterruptedException {
		List<Station> allStatii = LiveDataTestUtil.getValue(stationDao.getAllStationsLiveData());
		Assert.assertEquals(0, allStatii.size());
	}

	@Test
	public void testInsertInTable() throws InterruptedException {
		Station station = new Station(0, "Test", "Tur", 7);
		stationDao.insertStations(station);
		List<Station> allStatii = LiveDataTestUtil.getValue(stationDao.getAllStationsLiveData());
		Assert.assertEquals(station.getName(), allStatii.get(0).getName());
	}

	@Test
	public void testInsertedObject() throws InterruptedException {
		Station station = generateMockStations().get(0);
		stationDao.insertStations(station);
		List<Station> allStatii = LiveDataTestUtil.getValue(stationDao.getAllStationsLiveData());
		Assert.assertEquals(station, allStatii.get(0));
	}

	@Test
	public void testSecondInsert() throws InterruptedException {
		Station station = generateMockStations().get(0);
		stationDao.insertStations(station);
		Station station2 = generateMockStations().get(1);
		stationDao.insertStations(station2);
		List<Station> allStatii = LiveDataTestUtil.getValue(stationDao.getAllStationsLiveData());
		Assert.assertEquals(station2, allStatii.get(1));
	}

	@Test
	public void testDeleteBilet() throws InterruptedException {
		Station station = generateMockStations().get(0);
		stationDao.insertStations(station);
		stationDao.deleteStations(station);
		List<Station> allStatii = LiveDataTestUtil.getValue(stationDao.getAllStationsLiveData());
		Assert.assertEquals(0, allStatii.size());
	}

	@Test
	public void testInsertAllBilet() throws InterruptedException {
		Station station = generateMockStations().get(0);
		Station station2 = generateMockStations().get(1);
		stationDao.insertStations(station, station2);
		List<Station> allStatii = LiveDataTestUtil.getValue(stationDao.getAllStationsLiveData());
		Assert.assertEquals(2, allStatii.size());
	}

	@Test
	public void testDeleteAllBilet() throws InterruptedException {
		Station station = generateMockStations().get(0);
		Station station2 = generateMockStations().get(1);
		stationDao.insertStations(station, station2);
		stationDao.deleteAll();

		LiveData<List<Station>> allStationsLiveData = stationDao.getAllStationsLiveData();
		assert allStationsLiveData != null;
		List<Station> allStatii = LiveDataTestUtil.getValue(allStationsLiveData);
		Assert.assertEquals(0, allStatii.size());
	}

	@Test
	public void testUpdateBilet() throws InterruptedException {
		Station station = generateMockStations().get(0);
		stationDao.insertStations(station);
		station.setName("TestNume");
		stationDao.updateStations(station);
		List<Station> allStatii = LiveDataTestUtil.getValue(stationDao.getAllStationsLiveData());
		Assert.assertEquals("TestNume", allStatii.get(0).getName());
	}

	@Test
	public void testUpdateSpecificBilet() throws InterruptedException {
		List<Station> stations = generateMockStations();

		Station station0 = stations.get(0);
		Station station1 = stations.get(1);
		Station station2 = stations.get(2);

		stationDao.insertStations(station0, station1, station2);
		station2.setName("TestNume");
		stationDao.updateStations(station2);
		List<Station> allStatii = LiveDataTestUtil.getValue(stationDao.getAllStationsLiveData());
		Assert.assertEquals("TestNume", allStatii.get(2).getName());
	}

	private List<Station> generateMockStations() {
		List<Station> localList = new ArrayList<>();
		localList.add(new Station(0, "Statie1", "Tur", 7));
		localList.add(new Station(1, "Statie2", "Tur", 7));
		localList.add(new Station(2, "Statie3", "Tur", 7));
		return localList;
	}

}
