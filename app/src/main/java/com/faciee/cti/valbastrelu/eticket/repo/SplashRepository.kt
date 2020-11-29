package com.faciee.cti.valbastrelu.eticket.repo

import com.faciee.cti.valbastrelu.eticket.api.Schedule
import com.faciee.cti.valbastrelu.eticket.room.EtkRoomDB
import com.faciee.cti.valbastrelu.eticket.room.entities.Route
import com.faciee.cti.valbastrelu.eticket.ui.common.TransportType
import com.faciee.cti.valbastrelu.eticket.util.DateUtils
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class SplashRepository(db: EtkRoomDB) {
	private val routesDao = db.routesDao()

	suspend fun clearSchedule(){
		routesDao.deleteAll()
	}

	suspend fun insertSchedules(schedule: Schedule?) {
		withContext(IO) {
			schedule?.run {
				convertToEntity(this)
			}?.forEach { route ->
				routesDao.insertRoutes(route)
			}
		}
	}

	@Suppress("UNUSED_VARIABLE")
	suspend fun convertToEntity(schedule: Schedule): List<Route> {
		return withContext(IO) {
			val busses = async { addRoutes(TransportType.BUS, schedule) }
			val trolleys = async { addRoutes(TransportType.TBUS, schedule) }
			val trams = async { addRoutes(TransportType.TRAM, schedule) }

			arrayListOf<Route>().apply {
				addAll(busses.await())
				addAll(trolleys.await())
//				addAll(trams.await())
			}
		}
	}

	private fun addRoutes(type: TransportType, schedule: Schedule): List<Route> {
		val routes = when (type) {
			TransportType.BUS -> schedule.bus
			TransportType.TBUS -> schedule.tbus
			TransportType.TRAM -> schedule.tram
			else -> emptyList()
		}
		return arrayListOf<Route>().apply {
			routes.forEach { apiRoute ->
				//TODO convert stations
				//TODO convert streets
				val startTime = DateUtils.formattedTime(apiRoute.start)
				val endTime = DateUtils.formattedTime(apiRoute.end)
				add(Route(apiRoute.routeNumber, startTime, endTime, apiRoute.weekdays, apiRoute.weekend, null, null, type))
			}
		}
	}
}