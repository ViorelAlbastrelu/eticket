package com.faciee.cti.valbastrelu.eticket.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.faciee.cti.valbastrelu.eticket.room.entities.Transaction
import java.util.Date

@Dao
interface TransactionsDao {
	@get:Query("SELECT * FROM `transaction`")
	val allTransactionsLiveData: LiveData<List<Transaction>>

	@Query("SELECT * FROM `transaction` WHERE date = :date")
	fun getTransactionsStartingFrom(date: Date): List<Transaction>

	@Insert
	suspend fun insertTransactions(vararg transaction: Transaction?)

	@Update
	fun updateTransactions(vararg transaction: Transaction?)

	@Delete
	fun deleteTransactions(vararg transaction: Transaction?)

	@Query("DELETE FROM `transaction`")
	fun deleteAll()
}