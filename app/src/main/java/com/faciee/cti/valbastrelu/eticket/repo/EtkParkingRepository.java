package com.faciee.cti.valbastrelu.eticket.repo;

import androidx.lifecycle.LiveData;
import android.os.AsyncTask;

import com.faciee.cti.valbastrelu.eticket.room.EtkRoomDB;
import com.faciee.cti.valbastrelu.eticket.room.dao.BiletPDao;
import com.faciee.cti.valbastrelu.eticket.room.dao.TranzactieDao;
import com.faciee.cti.valbastrelu.eticket.room.entities.BiletP;
import com.faciee.cti.valbastrelu.eticket.room.entities.Tranzactie;
import com.faciee.cti.valbastrelu.eticket.ui.common.TransportType;

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
		new InsertBiletAsync(biletPDao, tranzactieDao).execute(bilet);
	}
	
	private static class InsertBiletAsync extends AsyncTask<BiletP, Void, Void> {
		private BiletPDao biletPDao;
		private TranzactieDao tranzactieDao;
		
		public InsertBiletAsync(BiletPDao biletDao, TranzactieDao tranzactieDao) {
			this.biletPDao = biletDao;
			this.tranzactieDao = tranzactieDao;
		}
		
		@Override
		protected Void doInBackground(BiletP... bilete) {
			biletPDao.updateBileteStatus(false);
			biletPDao.insertBilete(bilete);
			tranzactieDao.insertTranzactii(new Tranzactie(bilete[0].getIdbiletp(), bilete[0].getData(),
					TransportType.PARKING, 0, bilete[0].getPret() * -1));
			return null;
		}
	}
}
