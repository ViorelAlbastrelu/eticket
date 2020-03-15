package com.faciee.cti.valbastrelu.eticket.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.faciee.cti.valbastrelu.eticket.room.entities.Tranzactie;

import java.util.List;

@Dao
public interface TranzactieDao {
	
	@Query("SELECT * FROM tranzactie")
	LiveData<List<Tranzactie>> getAllTranzactii();
	
//	@Query("SELECT * FROM tranzactie WHERE data = :date")
//	List<Tranzactie> getTranzactiiStartingAtDate(Date date);
	
	@Insert
	void insertTranzactii(Tranzactie... tranzactie);
	
	@Update
	void updateTranzactii(Tranzactie... tranzactie);
	
	@Delete
	void deleteTranzactii(Tranzactie... tranzactie);
}
