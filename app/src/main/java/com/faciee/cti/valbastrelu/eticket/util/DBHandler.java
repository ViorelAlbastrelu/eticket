package com.faciee.cti.valbastrelu.eticket.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "etickets.db";
	
	//Tabel trasee
	private static final String TABLE_TRASEU = "traseu";
	private static final String COLUMN_IDT = "idt";
	private static final String COLUMN_NO = "no";
	private static final String COLUMN_TYPE = "type";
	
	//Tabel statii
	private static final String TABLE_STATIE = "statie";
	private static final String COLUMN_IDS = "ids";
	private static final String COLUMN_NAME = "name";
	private static final String COLUMN_WAY = "way";
	private static final String COLUMN_FK_IDT = COLUMN_IDT;
	
	//Tabel statii
	private static final String TABLE_ORAR = "orar";
	private static final String COLUMN_IDO = "ido";
	private static final String COLUMN_TIME = "time";
	private static final String COLUMN_FK_IDS = COLUMN_IDS;
	
	public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
		super(context, DATABASE_NAME, factory, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String createTableTraseu = "CREATE TABLE " + TABLE_TRASEU + "(" +
				COLUMN_IDT + " INTEGER PRIMARY KEY AUTOINCREMENT," +
				COLUMN_NO + " INTEGER, " +
				COLUMN_TYPE + " TEXT" + ")";
		db.execSQL(createTableTraseu);
		
		String createTableStatie = "CREATE TABLE " + TABLE_STATIE + "(" +
				COLUMN_IDS + " INTEGER PRIMARY KEY AUTOINCREMENT," +
				COLUMN_NAME + " TEXT, " +
				COLUMN_WAY + " TEXT " +
				COLUMN_FK_IDT + " INTEGER " + ")";
		db.execSQL(createTableStatie);
		
		String createTableOrar = "CREATE TABLE " + TABLE_TRASEU + "(" +
				COLUMN_IDT + " INTEGER PRIMARY KEY AUTOINCREMENT," +
				COLUMN_NO + " INTEGER, " +
				COLUMN_TYPE + " TEXT" + ")";
		db.execSQL(createTableOrar);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRASEU);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATIE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORAR);
		onCreate(db);
	}
}
