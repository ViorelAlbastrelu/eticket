package com.faciee.cti.valbastrelu.eticket.repo;

import android.arch.lifecycle.LiveData;

import com.faciee.cti.valbastrelu.eticket.room.EtkRoomDB;
import com.faciee.cti.valbastrelu.eticket.room.dao.BiletPDao;
import com.faciee.cti.valbastrelu.eticket.room.dao.TranzactieDao;
import com.faciee.cti.valbastrelu.eticket.room.entities.BiletP;
import com.faciee.cti.valbastrelu.eticket.room.entities.Tranzactie;

import java.util.List;

public class EtkParkingRepository {
	
	private static EtkParkingRepository INSTANCE;
	private final EtkRoomDB db;
	
	//Dao
	private BiletPDao biletPDao;
	private TranzactieDao tranzactieDao;
	
	public EtkParkingRepository(EtkRoomDB db) {
		this.db = db;
		biletPDao = this.db.biletpDao();
		tranzactieDao = this.db.tranzactieDao();
	}
	
	public static EtkParkingRepository getInstance(final EtkRoomDB database) {
		if (INSTANCE == null) {
			synchronized (EtkParkingRepository.class) {
				if (INSTANCE == null) {
					INSTANCE = new EtkParkingRepository(database);
				}
			}
		}
		return INSTANCE;
	}
	
	public LiveData<List<BiletP>> getBileteParcare(){
		return biletPDao.getAllBilete();
	}
	
	public LiveData<List<Tranzactie>> getLiveDataTranzactii() {
		return tranzactieDao.getAllTranzactii();
	}
	
	public void insertBilet(BiletP bilet) {
		biletPDao.insertBilete(bilet);
	}
}
