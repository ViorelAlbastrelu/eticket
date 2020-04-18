package com.faciee.cti.valbastrelu.eticket.repo;

import androidx.lifecycle.LiveData;

import android.os.AsyncTask;

import com.faciee.cti.valbastrelu.eticket.room.EtkRoomDB;
import com.faciee.cti.valbastrelu.eticket.room.dao.TicketParkingDao;
import com.faciee.cti.valbastrelu.eticket.room.dao.TransactionsDao;
import com.faciee.cti.valbastrelu.eticket.room.entities.TicketParking;
import com.faciee.cti.valbastrelu.eticket.room.entities.Transaction;
import com.faciee.cti.valbastrelu.eticket.ui.common.TransportType;

import java.math.BigDecimal;
import java.util.List;

public class EtkParkingRepository {

	private static EtkParkingRepository INSTANCE;
	private final EtkRoomDB db;

	//Dao
	private TicketParkingDao ticketParkingDao;
	private TransactionsDao transactionsDao;

	public EtkParkingRepository(EtkRoomDB db) {
		this.db = db;
		ticketParkingDao = this.db.biletpDao();
		transactionsDao = this.db.tranzactieDao();
	}

	public static EtkParkingRepository getInstance(final EtkRoomDB database) {
		if (INSTANCE == null) {
			synchronized (EtkParkingRepository.class) {
				if (INSTANCE == null) {
					INSTANCE = new EtkParkingRepository(database);
				}
			}
		}
		return INSTANCE;
	}

	public LiveData<List<TicketParking>> getBileteParcare() {
		return ticketParkingDao.getAllTicketsLiveData();
	}

	public LiveData<List<Transaction>> getLiveDataTranzactii() {
		return transactionsDao.getAllTransactionsLiveData();
	}

	public void insertBilet(TicketParking bilet) {
		new InsertBiletAsync(ticketParkingDao, transactionsDao).execute(bilet);
	}

	private static class InsertBiletAsync extends AsyncTask<TicketParking, Void, Void> {
		private TicketParkingDao ticketParkingDao;
		private TransactionsDao transactionsDao;

		public InsertBiletAsync(TicketParkingDao biletDao, TransactionsDao transactionsDao) {
			this.ticketParkingDao = biletDao;
			this.transactionsDao = transactionsDao;
		}

		@Override
		protected Void doInBackground(TicketParking... bilete) {
			for (TicketParking parking : bilete) {
				ticketParkingDao.updateTicketActiveStatus(parking.getId(), false);
				ticketParkingDao.insertTickets(bilete);
				transactionsDao.insertTransactions(new Transaction(bilete[0].getId(), bilete[0].getDate(),
						TransportType.PARKING, 0, bilete[0].getPrice().multiply(new BigDecimal(-1.0))));
			}
			return null;
		}
	}
}
