package com.faciee.cti.valbastrelu.eticket.ui.bus.model;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.faciee.cti.valbastrelu.eticket.base.AbstractAndroidViewModel;
import com.faciee.cti.valbastrelu.eticket.base.ETicketApp;
import com.faciee.cti.valbastrelu.eticket.repo.ETkBusRepository;
import com.faciee.cti.valbastrelu.eticket.room.entities.Bilet;
import com.faciee.cti.valbastrelu.eticket.room.entities.Tranzactie;
import com.faciee.cti.valbastrelu.eticket.room.entities.Traseu;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BusViewModel extends AbstractAndroidViewModel {
	private static final String TAG = "BusActivityModel";
	
	private ETkBusRepository repository;
	
	private Bilet biletActiv = null;
	LiveData<List<Bilet>> bilete;


	public BusViewModel(@NotNull ETicketApp application) {
		super(application);
		repository = application.getBusRepository();
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
