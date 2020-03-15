package com.faciee.cti.valbastrelu.eticket.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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
