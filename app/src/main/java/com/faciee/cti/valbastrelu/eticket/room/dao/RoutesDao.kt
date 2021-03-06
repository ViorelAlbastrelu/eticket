package com.faciee.cti.valbastrelu.eticket.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.faciee.cti.valbastrelu.eticket.room.entities.Route

@Dao
interface RoutesDao {
	@get:Query("SELECT * FROM route")
	val allRoutesLiveData: LiveData<List<Route?>?>?

	@Insert
	fun insertRoutes(vararg route: Route?)

	@Update
	fun updateRoutes(vararg route: Route?)

	@Delete
	fun deleteRoutes(vararg route: Route?)

	@Query("DELETE FROM route")
	fun deleteAll()
}