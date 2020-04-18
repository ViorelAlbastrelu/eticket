package com.faciee.cti.valbastrelu.eticket.repo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import android.os.AsyncTask;

import com.faciee.cti.valbastrelu.eticket.room.EtkRoomDB;
import com.faciee.cti.valbastrelu.eticket.room.dao.TicketDao;
import com.faciee.cti.valbastrelu.eticket.room.dao.StationDao;
import com.faciee.cti.valbastrelu.eticket.room.dao.TransactionsDao;
import com.faciee.cti.valbastrelu.eticket.room.entities.Ticket;
import com.faciee.cti.valbastrelu.eticket.room.entities.Route;
import com.faciee.cti.valbastrelu.eticket.ui.common.TransportType;
import com.faciee.cti.valbastrelu.eticket.room.entities.Transaction;
import com.faciee.cti.valbastrelu.eticket.util.DummyData;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;

public class ETkBusRepository {
	
	private static ETkBusRepository INSTANCE;
	private final EtkRoomDB db;
	
	//Dao
	private TicketDao ticketDao;
	private StationDao stationDao;
	private TransactionsDao transactionsDao;
	
	//Livedata
	private MutableLiveData<List<Ticket>> bileteLiveData;
	private MutableLiveData<List<Route>> traseeLiveData;
	private MutableLiveData<List<Transaction>> tranzactiiLiveData;
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
		ticketDao = db.biletDao();
		stationDao = db.statieDao();
		transactionsDao = db.tranzactieDao();
	}
	
	public LiveData<List<Ticket>> getBilete(){
		return ticketDao.getAllTicketsLiveData();
	}
	
	public LiveData<List<Transaction>> getLiveDataTranzactii(){
		return transactionsDao.getAllTransactionsLiveData();
//		if (tranzactiiLiveData == null){
//			tranzactiiLiveData = new MutableLiveData<>();
//			loadIstorice();
//		}
//		return tranzactiiLiveData;
	}
	public LiveData<List<Route>> getLiveDataTrasee(){
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

	public void insertBilet(Ticket ticket){
		new InsertBiletAsync(ticketDao, transactionsDao).execute(ticket);
	}

	private static class InsertBiletAsync extends AsyncTask<Ticket, Void, Void>{
		private TicketDao ticketDao;
		private TransactionsDao transactionsDao;
		
		public InsertBiletAsync(TicketDao ticketDao, TransactionsDao transactionsDao) {
			this.ticketDao = ticketDao;
			this.transactionsDao = transactionsDao;
		}

		@Override
		protected Void doInBackground(Ticket... bilete) {
			for (Ticket ticket : bilete) {
				ticketDao.updateTicketActiveStatus(ticket.getId(), false);
				ticketDao.insertTickets(bilete);
				BigDecimal price = bilete[0].getPrice().multiply(BigDecimal.TEN, MathContext.DECIMAL32).setScale(2, RoundingMode.HALF_EVEN);
				transactionsDao.insertTransactions(new Transaction(bilete[0].getId(), Calendar.getInstance().getTime(),
						TransportType.BUS, bilete[0].getRouteNumber(), price));
			}
			return null;
		}
	}
}
