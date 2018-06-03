package com.faciee.cti.valbastrelu.eticket.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.faciee.cti.valbastrelu.eticket.ui.bus.model.Statie;

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
