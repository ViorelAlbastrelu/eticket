package com.faciee.cti.valbastrelu.eticket.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.faciee.cti.valbastrelu.eticket.room.entities.Traseu;

import java.util.List;

@Dao
public interface TraseuDao {
	
	@Query("SELECT * FROM traseu")
	LiveData<List<Traseu>> getAllBilete();
	
	@Insert
	void insertTrasee(Traseu...traseu);
	@Update
	void updateTrasee(Traseu...traseu);
	@Delete
	void deleteTrasee(Traseu...traseu);
}
