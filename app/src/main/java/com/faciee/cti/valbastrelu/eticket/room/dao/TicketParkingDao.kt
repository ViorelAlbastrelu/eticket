package com.faciee.cti.valbastrelu.eticket.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.faciee.cti.valbastrelu.eticket.room.entities.TicketParking

@Dao
interface TicketParkingDao {
	@get:Query("SELECT * FROM ticketparking")
	val allTicketsLiveData: LiveData<List<TicketParking>>

	@Insert
	fun insertTicket(ticket: TicketParking?)

	@Insert
	fun insertTickets(vararg arrayOfTickets: TicketParking?)

	@Update
	fun updateTickets(vararg arrayOfTickets: TicketParking?)

	@Query("UPDATE ticketparking SET active = :status WHERE id = :id")
	fun updateTicketActiveStatus(id: Long, status: Boolean)

	@Query("UPDATE ticketparking SET alertOn = :alert WHERE id = :id")
	fun updateTicketAlertStatus(id: Long, alert: Boolean)

	@Delete
	fun deleteTickets(vararg arrayOfTickets: TicketParking?)

	@Query("DELETE FROM ticketparking")
	fun deleteAll()
}