package com.faciee.cti.valbastrelu.eticket.ui.parking.model;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.faciee.cti.valbastrelu.eticket.main.ETicketApp;
import com.faciee.cti.valbastrelu.eticket.repo.EtkParkingRepository;
import com.faciee.cti.valbastrelu.eticket.room.entities.BiletP;
import com.faciee.cti.valbastrelu.eticket.room.entities.Tranzactie;
import com.faciee.cti.valbastrelu.eticket.ui.common.AbstractActivityViewModel;

import java.util.List;

public class ParkingActivityViewModel extends AbstractActivityViewModel {
	private static final String TAG = "ParkingActivityModel";
	private EtkParkingRepository repository;
	
	LiveData<List<BiletP>> biletep;
	
	public ParkingActivityViewModel(@NonNull Application application) {
		super(application);
		repository = ETicketApp.getCurrentApplication().getParkingRepository();
		biletep = repository.getBileteParcare();
	}
	
	public LiveData<List<BiletP>> getLiveDataBilete(){
		return repository.getBileteParcare();
	}
	
	public LiveData<List<Tranzactie>> getLiveDataTranzactii(){
		return repository.getLiveDataTranzactii();
	}
	
	public void insertBilet(BiletP bilet){
		repository.insertBilet(bilet);
	}
	
}
