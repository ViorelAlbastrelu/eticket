package com.faciee.cti.valbastrelu.eticket.util.repo;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.faciee.cti.valbastrelu.eticket.main.ETicketApp;
import com.faciee.cti.valbastrelu.eticket.util.model.Bilet;
import com.faciee.cti.valbastrelu.eticket.util.model.Statie;
import com.faciee.cti.valbastrelu.eticket.util.room.BiletDao;
import com.faciee.cti.valbastrelu.eticket.util.room.ETkRoom;
import com.faciee.cti.valbastrelu.eticket.util.room.StatieDao;

import java.util.List;

public class ETkRepository {
	
	private StatieDao statieDao;
	private BiletDao biletDao;
	
	public ETkRepository(ETicketApp eTicketApp) {
		ETkRoom db = ETkRoom.getDatabase(eTicketApp);
		statieDao = db.getStatieDao();
		biletDao = db.getBiletDao();
	}
	
	LiveData<List<Statie>> getStatieForTraseu(int nrTraseu){
		return statieDao.getStatiiForTraseu(nrTraseu);
	}
	
	LiveData<List<Bilet>> getBilete(){
		return biletDao.getAllBilete();
	}
	
	void insertBilet(Bilet bilet){
		new InsertBiletAsync(biletDao).execute(bilet);
	}
	
	private static class InsertBiletAsync extends AsyncTask<Bilet, Void, Void>{
		private BiletDao biletDao;
		
		InsertBiletAsync(BiletDao biletDao) {
			this.biletDao = biletDao;
		}
		
		@Override
		protected Void doInBackground(Bilet... bilets) {
			biletDao.insertBilete(bilets);
			return null;
		}
	}
}
