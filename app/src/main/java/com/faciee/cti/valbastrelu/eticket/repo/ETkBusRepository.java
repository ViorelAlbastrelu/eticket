package com.faciee.cti.valbastrelu.eticket.repo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.loader.content.AsyncTaskLoader;

import android.content.Context;
import android.os.AsyncTask;

import com.faciee.cti.valbastrelu.eticket.room.EtkRoomDB;
import com.faciee.cti.valbastrelu.eticket.room.dao.BiletDao;
import com.faciee.cti.valbastrelu.eticket.room.dao.StatieDao;
import com.faciee.cti.valbastrelu.eticket.room.dao.TranzactieDao;
import com.faciee.cti.valbastrelu.eticket.room.entities.Bilet;
import com.faciee.cti.valbastrelu.eticket.ui.common.TransportType;
import com.faciee.cti.valbastrelu.eticket.room.entities.Tranzactie;
import com.faciee.cti.valbastrelu.eticket.room.entities.Traseu;
import com.faciee.cti.valbastrelu.eticket.util.DummyData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ETkBusRepository {
	
	private static ETkBusRepository INSTANCE;
	private final EtkRoomDB db;
	
	//Dao
	private BiletDao biletDao;
	private StatieDao statieDao;
	private TranzactieDao tranzactieDao;
	
	//Livedata
	private MutableLiveData<List<Bilet>> bileteLiveData;
	private MutableLiveData<List<Traseu>> traseeLiveData;
	private MutableLiveData<List<Tranzactie>> tranzactiiLiveData;
	private StatiiLiveData statiiLiveData;
	
	public static ETkBusRepository getInstance(final EtkRoomDB database) {
		if (INSTANCE == null) {
			synchronized (ETkBusRepository.class) {
				if (INSTANCE == null) {
					INSTANCE = new ETkBusRepository(database);
				}
			}
		}
		return INSTANCE;
	}
	
	public ETkBusRepository(EtkRoomDB roomDB) {
		this.db = roomDB;
		biletDao = db.biletDao();
		statieDao = db.statieDao();
		tranzactieDao = db.tranzactieDao();
	}
	
	public LiveData<List<Bilet>> getBilete(){
		return biletDao.getAllBilete();
	}
	
	public LiveData<List<Tranzactie>> getLiveDataTranzactii(){
		return tranzactieDao.getAllTranzactii();
//		if (tranzactiiLiveData == null){
//			tranzactiiLiveData = new MutableLiveData<>();
//			loadIstorice();
//		}
//		return tranzactiiLiveData;
	}
	public LiveData<List<Traseu>> getLiveDataTrasee(){
		if (traseeLiveData == null){
			traseeLiveData = new MutableLiveData<>();
			traseeLiveData.setValue(DummyData.loadTrasee());
		}
		return traseeLiveData;
	}
	public LiveData<List<String>> getLiveDataStatii(int nrTraseu){
		statiiLiveData = new StatiiLiveData(nrTraseu);
		return statiiLiveData.getNumeStatii();
	}
//	LiveData<List<Statie>> getStatieForTraseu(int nrTraseu){
//		return statieDao.getStatiiForTraseu(nrTraseu);
//	}

	public void insertBilet(Bilet bilet){
		new InsertBiletAsync(biletDao, tranzactieDao).execute(bilet);
	}

	private static class InsertBiletAsync extends AsyncTask<Bilet, Void, Void>{
		private BiletDao biletDao;
		private TranzactieDao tranzactieDao;
		
		public InsertBiletAsync(BiletDao biletDao, TranzactieDao tranzactieDao) {
			this.biletDao = biletDao;
			this.tranzactieDao = tranzactieDao;
		}

		@Override
		protected Void doInBackground(Bilet... bilete) {
			biletDao.updateBileteStatus(false);
			biletDao.insertBilete(bilete);
			tranzactieDao.insertTranzactii(new Tranzactie(bilete[0].getIdbilet(), Calendar.getInstance().getTime(),
					TransportType.BUS, bilete[0].getTraseu(), bilete[0].getPret()  * -1));
			return null;
		}
	}
}
