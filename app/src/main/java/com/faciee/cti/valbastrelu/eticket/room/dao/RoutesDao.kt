package com.faciee.cti.valbastrelu.eticket.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.faciee.cti.valbastrelu.eticket.room.entities.Route
import com.faciee.cti.valbastrelu.eticket.ui.common.TransportType

@Dao
interface RoutesDao {
	@get:Query("SELECT * FROM route")
	val allRoutesLiveData: LiveData<List<Route>>

	@Query("SELECT * FROM route WHERE transportType = :type")
	suspend fun getRoutesForType(type: TransportType): List<Route>

	@Query("SELECT * FROM route WHERE transportType = :type ORDER BY startTime LIMIT 1")
	suspend fun getLatestRoute(type: TransportType): Route

	@Query("SELECT number, weekOffset, weekendOffset, MIN(startTime) as startTime, endTime, transportType FROM route GROUP BY transportType")
	suspend fun getLatestRoutes(): List<Route>

	@Insert
	suspend fun insertRoutes(vararg route: Route)

	@Update
	suspend fun updateRoutes(vararg route: Route)

	@Delete
	suspend fun deleteRoutes(vararg route: Route)

	@Query("DELETE FROM route")
	suspend fun deleteAll()
}