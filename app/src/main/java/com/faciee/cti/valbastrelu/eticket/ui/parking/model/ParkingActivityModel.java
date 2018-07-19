package com.faciee.cti.valbastrelu.eticket.ui.parking.model;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.faciee.cti.valbastrelu.eticket.main.ETicketApp;
import com.faciee.cti.valbastrelu.eticket.repo.EtkParkingRepository;
import com.faciee.cti.valbastrelu.eticket.room.entities.BiletP;
import com.faciee.cti.valbastrelu.eticket.room.entities.Tranzactie;
import com.faciee.cti.valbastrelu.eticket.ui.common.AbstractActivityModel;

import java.util.List;

public class ParkingActivityModel extends AbstractActivityModel {
	private static final String TAG = "ParkingActivityModel";
	private EtkParkingRepository repository;
	
	LiveData<List<BiletP>> biletep;
	
	public ParkingActivityModel(@NonNull Application application) {
		super(application);
		repository = ETicketApp.getCurrentApplication().getParkingRepository();
		biletep = repository.getBileteParcare();
	}
	
	public LiveData<List<Tranzactie>> getLiveDataTranzactii(){
		return repository.getLiveDataTranzactii();
	}
	
	public void insertBilet(BiletP bilet){
		repository.insertBilet(bilet);
	}
	
}
