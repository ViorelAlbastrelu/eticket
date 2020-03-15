package com.faciee.cti.valbastrelu.eticket.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.faciee.cti.valbastrelu.eticket.room.entities.Bilet;

import java.util.List;

@Dao
public interface BiletDao {
	
	@Query("SELECT * FROM bilet")
	LiveData<List<Bilet>> getAllBilete();
	
	@Insert
	void insertOneBilet(Bilet bilet);
	@Insert
	void insertBilete(Bilet...bilet);
	
	@Update
	void updateBilete(Bilet...bilet);
	@Query("UPDATE bilet SET activ = :status")
	void updateBileteStatus(boolean status);
	
	@Delete
	void deleteBilete(Bilet... bilet);
	@Query("DELETE FROM bilet")
	void deleteAll();
}
