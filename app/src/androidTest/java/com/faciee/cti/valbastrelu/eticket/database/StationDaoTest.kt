package com.faciee.cti.valbastrelu.eticket.database

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.faciee.cti.valbastrelu.eticket.room.EtkRoomDB
import com.faciee.cti.valbastrelu.eticket.room.dao.StationDao
import com.faciee.cti.valbastrelu.eticket.room.entities.Station
import junit.framework.Assert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class StationDaoTest {
	private lateinit var stationDao: StationDao
	private lateinit var etkRoomDB: EtkRoomDB

	@Before
	fun createDb() {
		val context = InstrumentationRegistry.getInstrumentation().context
		etkRoomDB = Room.inMemoryDatabaseBuilder(context, EtkRoomDB::class.java)
				.allowMainThreadQueries()
				.build()
		stationDao = etkRoomDB.stationDao()
	}

	@After
	fun closeDb() {
		etkRoomDB.close()
	}

	@Test
	@Throws(InterruptedException::class)
	fun checkIfTableIsEmpty() {
		val allStatii = stationDao.allStationsLiveData.testableValue()
		Assert.assertEquals(0, allStatii.size)
	}

	@Test
	@Throws(InterruptedException::class)
	fun testInsertInTable() {
		val station = Station(0, "Test", "Tur", 7)
		stationDao.insertStations(station)
		val allStatii = stationDao.allStationsLiveData.testableValue()
		Assert.assertEquals(station.name, allStatii[0].name)
	}

	@Test
	@Throws(InterruptedException::class)
	fun testInsertedObject() {
		val station = generateMockStations()[0]
		stationDao.insertStations(station)
		val allStatii = stationDao.allStationsLiveData.testableValue()
		Assert.assertEquals(station, allStatii[0])
	}

	@Test
	@Throws(InterruptedException::class)
	fun testSecondInsert() {
		val station = generateMockStations()[0]
		stationDao.insertStations(station)
		val station2 = generateMockStations()[1]
		stationDao.insertStations(station2)
		val allStatii = stationDao.allStationsLiveData.testableValue()
		Assert.assertEquals(station2, allStatii[1])
	}

	@Test
	@Throws(InterruptedException::class)
	fun testDeleteBilet() {
		val station = generateMockStations()[0]
		stationDao.insertStations(station)
		stationDao.deleteStations(station)
		val allStatii = stationDao.allStationsLiveData.testableValue()
		Assert.assertEquals(0, allStatii.size)
	}

	@Test
	@Throws(InterruptedException::class)
	fun testInsertAllBilet() {
		val station = generateMockStations()[0]
		val station2 = generateMockStations()[1]
		stationDao.insertStations(station, station2)
		val allStatii = stationDao.allStationsLiveData.testableValue()
		Assert.assertEquals(2, allStatii.size)
	}

	@Test
	@Throws(InterruptedException::class)
	fun testDeleteAllBilet() {
		val station = generateMockStations()[0]
		val station2 = generateMockStations()[1]
		stationDao.insertStations(station, station2)
		stationDao.deleteAll()
		val allStationsLiveData = stationDao.allStationsLiveData.testableValue()
		Assert.assertEquals(0, allStationsLiveData.size)
	}

	@Test
	@Throws(InterruptedException::class)
	fun testUpdateBilet() {
		val station = generateMockStations()[0]
		stationDao.insertStations(station)
		station.name = "TestNume"
		stationDao.updateStations(station)
		val allStatii = stationDao.allStationsLiveData.testableValue()
		Assert.assertEquals("TestNume", allStatii[0].name)
	}

	@Test
	@Throws(InterruptedException::class)
	fun testUpdateSpecificBilet() {
		val stations = generateMockStations()
		val station0 = stations[0]
		val station1 = stations[1]
		val station2 = stations[2]
		stationDao.insertStations(station0, station1, station2)
		station2.name = "TestNume"
		stationDao.updateStations(station2)
		val allStatii = stationDao.allStationsLiveData.testableValue()
		Assert.assertEquals("TestNume", allStatii[2].name)
	}

	private fun generateMockStations(): List<Station> {
		val localList: MutableList<Station> = ArrayList()
		localList.add(Station(0, "Statie1", "Tur", 7))
		localList.add(Station(1, "Statie2", "Tur", 7))
		localList.add(Station(2, "Statie3", "Tur", 7))
		return localList
	}
}