package com.faciee.cti.valbastrelu.eticket.util.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.faciee.cti.valbastrelu.eticket.util.model.Bilet;
import com.faciee.cti.valbastrelu.eticket.util.model.Statie;
import com.faciee.cti.valbastrelu.eticket.util.model.Tranzactie;
import com.faciee.cti.valbastrelu.eticket.util.model.Traseu;

@Database(entities = {Bilet.class, Tranzactie.class, Statie.class, Traseu.class}, version = 1)
public abstract class ETkRoom extends RoomDatabase {
	
	public abstract BiletDao getBiletDao();
	public abstract TranzactieDao getTranzactieDao();
	public abstract StatieDao getStatieDao();
	public abstract TraseuDao getTraseuDao();
	public static ETkRoom INSTANCE;
	
	public static ETkRoom getDatabase(final Context context) {
		if (INSTANCE != null) {
			synchronized (ETkRoom.class) {
				if (INSTANCE == null) {
					INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ETkRoom.class, "etk_db").addCallback(callback).build();
				}
			}
		}
		return INSTANCE;
	}
	
	//TODO remove this bit maybe?
	private static ETkRoom.Callback callback = new Callback() {
		@Override
		public void onOpen(@NonNull SupportSQLiteDatabase db) {
			super.onOpen(db);
			new PopulateDbAsync(INSTANCE).execute();
		}
	};
	
	private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{
		BiletDao biletDao;
		
		PopulateDbAsync(ETkRoom instance) {
			biletDao = instance.getBiletDao();
		}
		
		@Override
		protected Void doInBackground(Void... voids) {
			biletDao.deleteAll();
			biletDao.insertBilete(new Bilet(102, true, 2,2));
			biletDao.insertBilete(new Bilet(7, false, 2,2));
			return null;
		}
	}
}
