package com.faciee.cti.valbastrelu.eticket.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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
	
	@Query("UPDATE biletp SET alerta = :alerta WHERE idbiletp = :idbilet")
	void updateAlertaBilet(boolean alerta, long idbilet);
	
	@Delete
	void deleteBilete(BiletP... bilet);
	@Query("DELETE FROM biletp")
	void deleteAll();
}
