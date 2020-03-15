package com.faciee.cti.valbastrelu.eticket.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.faciee.cti.valbastrelu.eticket.room.entities.Statie;

import java.util.List;

@Dao
public interface StatieDao {
	
	@Query("SELECT * FROM statie")
	LiveData<List<Statie>> getAllStatii();
	
	@Query("SELECT numeStatie FROM statie WHERE traseu = :nrTraseu")
	LiveData<List<String>> getStatiiForTraseu(int nrTraseu);
	
	@Insert
	void insertStatii(Statie... statie);
	
	@Update
	void updateStatii(Statie... statie);
	
	@Delete
	void deleteStatii(Statie... statie);
	@Query("DELETE FROM statie")
	void deleteAllStatii();
}
