package com.faciee.cti.valbastrelu.eticket.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.faciee.cti.valbastrelu.eticket.room.entities.BiletP;

import java.util.List;

@Dao
public interface BiletPDao {
	@Query("SELECT * FROM biletp")
	LiveData<List<BiletP>> getAllBilete();
	
	@Insert
	void insertOneBilet(BiletP bilet);
	@Insert
	void insertBilete(BiletP...bilet);
	
	@Update
	void updateBilete(BiletP...bilet);
	@Query("UPDATE biletp SET activ = :status")
	void updateBileteStatus(boolean status);
	
	@Delete
	void deleteBilete(BiletP... bilet);
	@Query("DELETE FROM biletp")
	void deleteAll();
}
