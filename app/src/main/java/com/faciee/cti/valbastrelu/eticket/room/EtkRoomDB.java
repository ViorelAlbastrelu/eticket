package com.faciee.cti.valbastrelu.eticket.room;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.faciee.cti.valbastrelu.eticket.room.dao.BiletDao;
import com.faciee.cti.valbastrelu.eticket.room.dao.BiletPDao;
import com.faciee.cti.valbastrelu.eticket.room.dao.StatieDao;
import com.faciee.cti.valbastrelu.eticket.room.dao.TranzactieDao;
import com.faciee.cti.valbastrelu.eticket.room.entities.Bilet;
import com.faciee.cti.valbastrelu.eticket.room.entities.BiletP;
import com.faciee.cti.valbastrelu.eticket.room.entities.Statie;
import com.faciee.cti.valbastrelu.eticket.room.entities.Tranzactie;

@Database(entities = {Bilet.class, BiletP.class, Tranzactie.class, Statie.class}, version = 6, exportSchema = false)
public abstract class EtkRoomDB extends RoomDatabase {
	
	public abstract BiletDao biletDao();
	public abstract BiletPDao biletpDao();
	public abstract TranzactieDao tranzactieDao();
	public abstract StatieDao statieDao();
	private static EtkRoomDB INSTANCE;
	
	public static EtkRoomDB getDatabase(final Context context) {
		if (INSTANCE == null) {
			synchronized (EtkRoomDB.class) {
				if (INSTANCE == null) {
					INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
							EtkRoomDB.class, "word_database")
							// Wipes and rebuilds instead of migrating if no Migration object.
							// Migration is not part of this codelab.
							.fallbackToDestructiveMigration()
							.build();
				}
			}
		}
		return INSTANCE;
	}
}
