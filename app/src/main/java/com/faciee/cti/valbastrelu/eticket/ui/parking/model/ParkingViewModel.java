package com.faciee.cti.valbastrelu.eticket.ui.parking.model;

import androidx.lifecycle.LiveData;

import com.faciee.cti.valbastrelu.eticket.base.AbstractAndroidViewModel;
import com.faciee.cti.valbastrelu.eticket.base.ETicketApp;
import com.faciee.cti.valbastrelu.eticket.repo.EtkParkingRepository;
import com.faciee.cti.valbastrelu.eticket.room.entities.TicketParking;
import com.faciee.cti.valbastrelu.eticket.room.entities.Transaction;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ParkingViewModel extends AbstractAndroidViewModel {
	private static final String TAG = "ParkingActivityModel";
	private EtkParkingRepository repository;
	
	LiveData<List<TicketParking>> biletep;

	public ParkingViewModel(@NotNull ETicketApp application) {
		super(application);
		repository = application.getParkingRepository();
		biletep = repository.getBileteParcare();
	}
	
	public LiveData<List<TicketParking>> getLiveDataBilete(){
		return repository.getBileteParcare();
	}
	
	public LiveData<List<Transaction>> getLiveDataTranzactii(){
		return repository.getLiveDataTranzactii();
	}
	
	public void insertBilet(TicketParking bilet){
		repository.insertBilet(bilet);
	}
	
}
