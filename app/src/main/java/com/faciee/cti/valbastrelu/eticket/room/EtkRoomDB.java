package com.faciee.cti.valbastrelu.eticket.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.faciee.cti.valbastrelu.eticket.room.dao.BiletDao;
import com.faciee.cti.valbastrelu.eticket.room.dao.StatieDao;
import com.faciee.cti.valbastrelu.eticket.ui.bus.model.Bilet;
import com.faciee.cti.valbastrelu.eticket.ui.bus.model.Statie;

@Database(entities = {Bilet.class, Statie.class}, version = 3, exportSchema = false)
public abstract class EtkRoomDB extends RoomDatabase {
	
	public abstract BiletDao biletDao();
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
