package com.faciee.cti.valbastrelu.eticket.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.faciee.cti.valbastrelu.eticket.room.entities.TicketParking

@Dao
interface TicketParkingDao {
	@get:Query("SELECT * FROM ticketparking")
	val allTicketsLiveData: LiveData<List<TicketParking>>

	@get:Query("SELECT * FROM ticketparking WHERE active = 1 ORDER BY date LIMIT 1")
	val recentTicket: TicketParking?

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertTicket(ticket: TicketParking?)

	@Insert
	suspend fun insertTickets(vararg arrayOfTickets: TicketParking?)

	@Update
	suspend fun updateTickets(vararg arrayOfTickets: TicketParking?)

	@Query("UPDATE ticketparking SET active = :status WHERE id = :id")
	suspend fun updateTicketActiveStatus(id: Long, status: Boolean)

	@Query("UPDATE ticketparking SET alertOn = :alert WHERE id = :id")
	suspend fun updateTicketAlertStatus(id: Long, alert: Boolean)

	@Delete
	suspend fun deleteTickets(vararg arrayOfTickets: TicketParking?)

	@Query("DELETE FROM ticketparking")
	suspend fun deleteAll()
}