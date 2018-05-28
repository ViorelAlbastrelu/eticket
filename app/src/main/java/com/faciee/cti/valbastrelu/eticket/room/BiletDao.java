package com.faciee.cti.valbastrelu.eticket.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.faciee.cti.valbastrelu.eticket.ui.bus.model.Bilet;

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
