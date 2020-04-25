package com.faciee.cti.valbastrelu.eticket.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.faciee.cti.valbastrelu.eticket.room.entities.Ticket

@Dao
interface TicketDao {
	@get:Query("SELECT * FROM ticket")
	val allTicketsLiveData: LiveData<List<Ticket>>

	@get:Query("SELECT * FROM ticket WHERE active = 1 ORDER BY date LIMIT 1")
	val recentTicket: Ticket

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertTicket(ticket: Ticket)

	@Insert
	suspend fun insertTickets(vararg ticket: Ticket)

	@Update
	suspend fun updateTickets(vararg ticket: Ticket)

	@Query("UPDATE ticket SET active = :status WHERE id = :id")
	suspend fun updateTicketActiveStatus(id: Long, status: Boolean)

	@Delete
	suspend fun deleteTickets(vararg arrayOfTickets: Ticket)

	@Query("DELETE FROM ticket")
	suspend fun deleteAll()
}