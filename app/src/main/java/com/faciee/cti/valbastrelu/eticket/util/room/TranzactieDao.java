package com.faciee.cti.valbastrelu.eticket.util.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.faciee.cti.valbastrelu.eticket.util.model.Tranzactie;

import java.util.Date;
import java.util.List;

@Dao
public interface TranzactieDao {
	
	@Query("SELECT * FROM tranzactie")
	List<Tranzactie> getAllTranzactii();
	
	@Query("SELECT * FROM tranzactie WHERE data = :date")
	List<Tranzactie> getTranzactiiStartingAtDate(Date date);
	
	@Insert
	void insertTranzactii(Tranzactie... tranzactie);
	
	@Update
	void updateTranzactii(Tranzactie... tranzactie);
	
	@Delete
	void deleteTranzactii(Tranzactie... tranzactie);
}
