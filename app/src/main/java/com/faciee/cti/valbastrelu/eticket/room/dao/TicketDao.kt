package com.faciee.cti.valbastrelu.eticket.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.faciee.cti.valbastrelu.eticket.room.entities.Ticket

@Dao
interface TicketDao {
	@get:Query("SELECT * FROM ticket")
	val allTicketsLiveData: LiveData<List<Ticket?>?>?

	@Insert
	fun insertTicket(ticket: Ticket?)

	@Insert
	fun insertTickets(vararg ticket: Ticket?)

	@Update
	fun updateTickets(vararg ticket: Ticket?)

	@Query("UPDATE ticket SET active = :status WHERE id = :id")
	fun updateTicketActiveStatus(id: Long, status: Boolean)

	@Delete
	fun deleteTickets(vararg arrayOfTickets: Ticket?)

	@Query("DELETE FROM ticket")
	fun deleteAll()
}