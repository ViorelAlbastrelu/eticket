package com.faciee.cti.valbastrelu.eticket.util.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

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
	
	@Delete
	void delete(Tranzactie tranzactie);
}
