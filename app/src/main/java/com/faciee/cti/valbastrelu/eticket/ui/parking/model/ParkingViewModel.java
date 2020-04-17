package com.faciee.cti.valbastrelu.eticket.ui.parking.model;

import androidx.lifecycle.LiveData;

import com.faciee.cti.valbastrelu.eticket.base.AbstractAndroidViewModel;
import com.faciee.cti.valbastrelu.eticket.base.ETicketApp;
import com.faciee.cti.valbastrelu.eticket.repo.EtkParkingRepository;
import com.faciee.cti.valbastrelu.eticket.room.entities.BiletP;
import com.faciee.cti.valbastrelu.eticket.room.entities.Tranzactie;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ParkingViewModel extends AbstractAndroidViewModel {
	private static final String TAG = "ParkingActivityModel";
	private EtkParkingRepository repository;
	
	LiveData<List<BiletP>> biletep;

	public ParkingViewModel(@NotNull ETicketApp application) {
		super(application);
		repository = application.getParkingRepository();
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
