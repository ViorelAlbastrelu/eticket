package com.faciee.cti.valbastrelu.eticket.util.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.faciee.cti.valbastrelu.eticket.util.model.Statie;

import java.util.List;

@Dao
public interface StatieDao {
	
	@Query("SELECT * FROM statie")
	LiveData<List<Statie>> getAllStatii();

	@Query("SELECT * FROM statie WHERE nrTraseu = :nrTraseu")
	LiveData<List<Statie>> getStatiiForTraseu(int nrTraseu);
	
	@Insert
	void insertStatii(Statie... statie);
	
	@Update
	void updateStatii(Statie... statie);
	
	@Delete
	void deleteStatii(Statie... statie);
}
