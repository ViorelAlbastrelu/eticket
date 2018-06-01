package com.faciee.cti.valbastrelu.eticket.repo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.faciee.cti.valbastrelu.eticket.room.BiletDao;
import com.faciee.cti.valbastrelu.eticket.room.StatieDao;
import com.faciee.cti.valbastrelu.eticket.ui.bus.model.StatiiLiveData;
import com.faciee.cti.valbastrelu.eticket.ui.bus.model.Bilet;
import com.faciee.cti.valbastrelu.eticket.ui.bus.model.TransportType;
import com.faciee.cti.valbastrelu.eticket.ui.bus.model.Tranzactie;
import com.faciee.cti.valbastrelu.eticket.ui.bus.model.Traseu;
import com.faciee.cti.valbastrelu.eticket.room.EtkRoomDB;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ETkRepository {
	
	//Dao
	private BiletDao biletDao;
	private StatieDao statieDao;
	
	private MutableLiveData<List<Bilet>> bileteLiveData;
	private MutableLiveData<List<Traseu>> traseeLiveData;
	private MutableLiveData<List<Tranzactie>> tranzactiiLiceData;
	private StatiiLiveData statiiLiveData;
	
	private SimpleDateFormat formatterDate = new SimpleDateFormat("dd-MMM-yyyy");
	private SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");
	
	public ETkRepository(Application application) {
		EtkRoomDB db = EtkRoomDB.getDatabase(application);
		biletDao = db.biletDao();
		statieDao = db.statieDao();
	}
	
	public LiveData<List<Bilet>> getBilete(){
//		if (bileteLiveData == null){
//			bileteLiveData = new MutableLiveData<>();
//			loadBilete();
//		}
		return biletDao.getAllBilete();
//TODO		return biletDao.getAllBilete();
	}
	public LiveData<List<Tranzactie>> getLiveDataTranzactii(){
		if (tranzactiiLiceData == null){
			tranzactiiLiceData = new MutableLiveData<>();
			loadIstorice();
		}
		return tranzactiiLiceData;
	}
	public LiveData<List<Traseu>> getLiveDataTrasee(){
		if (traseeLiveData == null){
			traseeLiveData = new MutableLiveData<>();
			loadTrasee();
		}
		return traseeLiveData;
	}
	public LiveData<List<String>> getLiveDataStatii(int nrTraseu){
		statiiLiveData = new StatiiLiveData(statieDao, nrTraseu);
		return statiiLiveData.getNumeStatii();
	}
	
	private void loadBilete() {
		List<Bilet> listaBilete = new ArrayList<>();
		listaBilete.add(new Bilet(102, true, 2,2));
		listaBilete.add(new Bilet(7, false, 2,2));
		listaBilete.add(new Bilet(44, false, 2,2));
		bileteLiveData.setValue(listaBilete);
	}
	private void loadTrasee() {
		List<Traseu> listaTrasee = new ArrayList<>();
		try {
			listaTrasee.add(new Traseu(39, formatterTime.parse("11:12"), TransportType.TRAM));
			listaTrasee.add(new Traseu(10, formatterTime.parse("11:32"), TransportType.BUS));
			listaTrasee.add(new Traseu(102, formatterTime.parse("11:45"), TransportType.TBUS));
			listaTrasee.add(new Traseu(7, formatterTime.parse("12:02"), TransportType.TRAM));
			listaTrasee.add(new Traseu(39, formatterTime.parse("12:12"), TransportType.TRAM));
			listaTrasee.add(new Traseu(10, formatterTime.parse("12:32"), TransportType.BUS));
			listaTrasee.add(new Traseu(102, formatterTime.parse("12:45"), TransportType.TBUS));
			listaTrasee.add(new Traseu(7, formatterTime.parse("13:02"), TransportType.TRAM));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		traseeLiveData.setValue(listaTrasee);
	}
	private void loadIstorice(){
		List<Tranzactie> listaIstorice = new ArrayList<>();
		try {
			listaIstorice.add(new Tranzactie(formatterDate.parse("12-JAN-2018"),TransportType.BUS,34, -2.00));
			listaIstorice.add(new Tranzactie(formatterDate.parse("13-JAN-2018"),TransportType.TBUS,102, -2.00));
			listaIstorice.add(new Tranzactie(formatterDate.parse("16-JAN-2018"),TransportType.TRAM,7, -2.00));
			listaIstorice.add(new Tranzactie(formatterDate.parse("22-JAN-2018"),TransportType.BUS,7, -2.00));
			listaIstorice.add(new Tranzactie(formatterDate.parse("23-JAN-2018"),TransportType.PARKING,7, -1.00));
			listaIstorice.add(new Tranzactie(formatterDate.parse("24-JAN-2018"),TransportType.NONE,0, +15.00));
			listaIstorice.add(new Tranzactie(formatterDate.parse("30-JAN-2018"),TransportType.TBUS,104, -2.00));
			listaIstorice.add(new Tranzactie(formatterDate.parse("02-FEB-2018"),TransportType.BUS,24, -2.00));
			listaIstorice.add(new Tranzactie(formatterDate.parse("10-FEB-2018"),TransportType.PARKING,7, -1.00));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		tranzactiiLiceData.setValue(listaIstorice);
	}
	private void loadStatiiTest(int nrTraseu) {
		List<String> listaStatii = new ArrayList<>();
		listaStatii.add("Micro 19-Cinema Dacia");
		listaStatii.add("Otelarilor");
		listaStatii.add("Bloc D19");
		listaStatii.add("Sala Sporturilor");
		listaStatii.add("Flora");
		listaStatii.add("Stadionul Otelul");
		listaStatii.add("Ghe. Doja");
		listaStatii.add("Piata Energiei T");
		listaStatii.add("Liceul 9");
		listaStatii.add("Piata Energiei R");
		listaStatii.add("ICFrimu");
		listaStatii.add("George Cosbuc");
		listaStatii.add("Posta Veche");
		listaStatii.add("Baia Comunala");
		listaStatii.add("Piata Centrala");
//		statiiLiveData.setValue(listaStatii);
	}
	
//	LiveData<List<Statie>> getStatieForTraseu(int nrTraseu){
//		return statieDao.getStatiiForTraseu(nrTraseu);
//	}


	public void insertBilet(Bilet bilet){
		new InsertBiletAsync(biletDao).execute(bilet);
	}

	private static class InsertBiletAsync extends AsyncTask<Bilet, Void, Void>{
		private BiletDao biletDao;

		InsertBiletAsync(BiletDao biletDao) {
			this.biletDao = biletDao;
		}

		@Override
		protected Void doInBackground(Bilet... bilets) {
			biletDao.updateBileteStatus(false);
			biletDao.insertBilete(bilets);
			return null;
		}
	}
}