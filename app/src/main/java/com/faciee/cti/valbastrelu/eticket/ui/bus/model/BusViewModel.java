package com.faciee.cti.valbastrelu.eticket.ui.bus.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.faciee.cti.valbastrelu.eticket.base.AbstractAndroidViewModel;
import com.faciee.cti.valbastrelu.eticket.base.ETicketApp;
import com.faciee.cti.valbastrelu.eticket.repo.ETkBusRepository;
import com.faciee.cti.valbastrelu.eticket.room.entities.Ticket;
import com.faciee.cti.valbastrelu.eticket.room.entities.Route;
import com.faciee.cti.valbastrelu.eticket.room.entities.Transaction;
import com.google.gson.annotations.Expose;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BusViewModel extends AbstractAndroidViewModel {
	private static final String TAG = "BusActivityModel";
	
	private ETkBusRepository repository;
	
	private Ticket ticketActiv = null;
	LiveData<List<Ticket>> bilete;


	public BusViewModel(@NotNull ETicketApp application) {
		super(application);
		repository = application.getBusRepository();
		bilete = repository.getBilete();
	}
	
	public void setTicketActiv(Ticket ticketActiv) {
		this.ticketActiv = ticketActiv;
	}
	
	public LiveData<List<Ticket>> getLiveDataBilete(){
		return bilete;
	}
	
	public LiveData<List<Route>> getLiveDataTrasee(){
		return repository.getLiveDataTrasee();
	}
	
	public LiveData<List<String>> getLiveDataStatii(int nrTraseu){
		return repository.getLiveDataStatii(nrTraseu);
	}
	
	public LiveData<List<Transaction>> getLiveDataTranzactii(){
		return repository.getLiveDataTranzactii();
	}
	
	//INSERTS
	public void insertBilet(Ticket ticket){
		setTicketActiv(ticket);
		repository.insertBilet(ticket);
	}
	
	@Override
	protected void onCleared() {
		super.onCleared();
		Log.d(TAG, "onCleared: called");
	}

	public static ViewModelProvider.Factory getFactory(ETicketApp app){
		return new ViewModelProvider.Factory() {

			@NonNull
			@Override
			public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
				return (T) new BusViewModel(app) ;
			}
		};
	}
}
