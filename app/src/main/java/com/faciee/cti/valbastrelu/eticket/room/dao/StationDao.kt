package com.faciee.cti.valbastrelu.eticket.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.faciee.cti.valbastrelu.eticket.room.entities.Station

@Dao
interface StationDao {
	@get:Query("SELECT * FROM station")
	val allStationsLiveData: LiveData<List<Station>>

	@Query("SELECT name FROM station WHERE routeNumber = :routeNumber")
	suspend fun getStationsForRoute(routeNumber: Int): List<String>

	@Insert
	suspend fun insertStations(vararg station: Station)

	@Update
	suspend fun updateStations(vararg station: Station)

	@Delete
	suspend fun deleteStations(vararg station: Station)

	@Query("DELETE FROM station")
	suspend fun deleteAll()
}