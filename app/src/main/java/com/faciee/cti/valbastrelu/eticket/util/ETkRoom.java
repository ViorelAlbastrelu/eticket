package com.faciee.cti.valbastrelu.eticket.util;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.faciee.cti.valbastrelu.eticket.util.model.Tranzactie;
import com.faciee.cti.valbastrelu.eticket.util.model.TranzactieDao;

@Database(entities = {Tranzactie.class}, version = 1)
public abstract class ETkRoom extends RoomDatabase {
	
//	public abstract TranzactieDao tranzactieDao;
	public static ETkRoom INSTANCE;
	
	public static ETkRoom getDatabase(final Context context) {
		if (INSTANCE != null) {
			synchronized (ETkRoom.class) {
				if (INSTANCE == null) {
					INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ETkRoom.class, "etk_db").build();
				}
			}
		}
		return INSTANCE;
	}
}
