package com.faciee.cti.valbastrelu.eticket.ui.bus.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.faciee.cti.valbastrelu.eticket.room.converter.DateConverter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Entity
public class Bilet {
	
	@PrimaryKey private long idbilet;
	@ColumnInfo private int traseu;
	@ColumnInfo private boolean activ;
	@ColumnInfo private int calatorii;
	@ColumnInfo private int pret;
	@TypeConverters(DateConverter.class) private Date data;
	
	public Bilet(int traseu, boolean activ, int pret, int calatorii) {
		idbilet = System.currentTimeMillis();
		this.traseu = traseu;
		this.activ = activ;
		this.calatorii = calatorii;
		this.pret = pret;
		this.data = Calendar.getInstance().getTime();
	}
	
	public long getIdbilet() {
		return idbilet;
	}
	
	public void setIdbilet(long idbilet) {
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
	
	public String getHourMinute() {
		return new SimpleDateFormat("HH:mm").format(data);
	}
	
	public String getDayMonthYear() {
		return new SimpleDateFormat("dd-MMM-yyyy").format(data);
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "Bilet{" +
				"idbilet=" + idbilet +
				", traseu=" + traseu +
				", activ=" + activ +
				", calatorii=" + calatorii +
				", pret=" + pret +
				", data=" + data +
				'}';
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Bilet bilet = (Bilet) o;
		return idbilet == bilet.idbilet &&
				traseu == bilet.traseu &&
				activ == bilet.activ &&
				calatorii == bilet.calatorii &&
				pret == bilet.pret &&
				Objects.equals(data, bilet.data);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idbilet, traseu, activ, calatorii, pret, data);
	}
}
