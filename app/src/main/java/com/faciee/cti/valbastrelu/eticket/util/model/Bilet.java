package com.faciee.cti.valbastrelu.eticket.util.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.faciee.cti.valbastrelu.eticket.util.room.DateConverter;

import java.util.Date;

@Entity
public class Bilet {
	
	@PrimaryKey(autoGenerate = true) private int idbilet;
	@ColumnInfo private int traseu;
	@ColumnInfo private boolean activ;
	@ColumnInfo private int calatorii;
	@ColumnInfo private int pret;
	@TypeConverters(DateConverter.class) private Date data;
	
	public Bilet(int traseu, boolean activ, int pret, int calatorii) {
		this.traseu = traseu;
		this.activ = activ;
		this.calatorii = calatorii;
		this.pret = pret;
//		this.data = Calendar.getInstance().getTime();
	}
	
	public int getIdbilet() {
		return idbilet;
	}
	
	public void setIdbilet(int idbilet) {
		this.idbilet = idbilet;
	}
	
	public int getTraseu() {
		return traseu;
	}
	
	public void setTraseu(int traseu) {
		this.traseu = traseu;
	}
	
	public boolean isActiv() {
		return activ;
	}
	
	public void setActiv(boolean activ) {
		this.activ = activ;
	}
	
	public int getCalatorii() {
		return calatorii;
	}
	
	public void setCalatorii(int calatorii) {
		this.calatorii = calatorii;
	}
	
	public int getPret() {
		return pret;
	}
	
	public void setPret(int pret) {
		this.pret = pret;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
}
