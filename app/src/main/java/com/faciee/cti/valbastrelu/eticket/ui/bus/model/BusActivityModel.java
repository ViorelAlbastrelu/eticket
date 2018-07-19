package com.faciee.cti.valbastrelu.eticket.ui.bus.model;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.faciee.cti.valbastrelu.eticket.main.ETicketApp;
import com.faciee.cti.valbastrelu.eticket.room.entities.Bilet;
import com.faciee.cti.valbastrelu.eticket.room.entities.Tranzactie;
import com.faciee.cti.valbastrelu.eticket.room.entities.Traseu;
import com.faciee.cti.valbastrelu.eticket.ui.common.AbstractActivityModel;
import com.faciee.cti.valbastrelu.eticket.repo.ETkBusRepository;

import java.util.List;

public class BusActivityModel extends AbstractActivityModel {
	private static final String TAG = "BusActivityModel";
	
	private ETkBusRepository repository;
	
	private Bilet biletActiv = null;
	LiveData<List<Bilet>> bilete;
	
	public BusActivityModel(@NonNull Application application) {
		super(application);
		repository = ETicketApp.getCurrentApplication().getBusRepository();
		bilete = repository.getBilete();
	}
	
	public void setBiletActiv(Bilet biletActiv) {
		this.biletActiv = biletActiv;
	}
	
	public LiveData<List<Bilet>> getLiveDataBilete(){
		return bilete;
	}
	
	public LiveData<List<Traseu>> getLiveDataTrasee(){
		return repository.getLiveDataTrasee();
	}
	
	public LiveData<List<String>> getLiveDataStatii(int nrTraseu){
		return repository.getLiveDataStatii(nrTraseu);
	}
	
	public LiveData<List<Tranzactie>> getLiveDataTranzactii(){
		return repository.getLiveDataTranzactii();
	}
	
	//INSERTS
	public void insertBilet(Bilet bilet){
		setBiletActiv(bilet);
		repository.insertBilet(bilet);
	}
	
	@Override
	protected void onCleared() {
		super.onCleared();
		Log.d(TAG, "onCleared: called");
	}
}
