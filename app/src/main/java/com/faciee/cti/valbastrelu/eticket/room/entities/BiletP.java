package com.faciee.cti.valbastrelu.eticket.room.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.faciee.cti.valbastrelu.eticket.room.converter.DateConverter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Entity
public class BiletP {
	
	@PrimaryKey private long idbiletp;
	@ColumnInfo private String locatie;
	@ColumnInfo private boolean activ;
	@ColumnInfo private double pret;
	@ColumnInfo private boolean alerta;
	@TypeConverters(DateConverter.class) private Date data;
	
	public BiletP(String locatie, boolean activ, double pret, boolean alerta) {
		this.idbiletp = System.currentTimeMillis();;
		this.locatie = locatie;
		this.activ = activ;
		this.pret = pret;
		this.alerta = alerta;
		this.data = Calendar.getInstance().getTime();
	}
	
	public long getIdbiletp() {
		return idbiletp;
	}
	
	public void setIdbiletp(long idbiletp) {
		this.idbiletp = idbiletp;
	}
	
	public String getLocatie() {
		return locatie;
	}
	
	public void setLocatie(String locatie) {
		this.locatie = locatie;
	}
	
	public boolean isActiv() {
		return activ;
	}
	
	public void setActiv(boolean activ) {
		this.activ = activ;
	}
	
	public double getPret() {
		return pret;
	}
	
	public void setPret(double pret) {
		this.pret = pret;
	}
	
	public boolean isAlerta() {
		return alerta;
	}
	
	public void setAlerta(boolean alerta) {
		this.alerta = alerta;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public String getHourMinute() {
		return new SimpleDateFormat("HH:mm").format(data);
	}
	
	public String getDayMonthYear() {
		return new SimpleDateFormat("dd-MMM-yyyy").format(data);
	}
	
	@Override
	public String toString() {
		return "BiletP{" +
				"idbiletp=" + idbiletp +
				", locatie='" + locatie + '\'' +
				", activ=" + activ +
				", pret=" + pret +
				", alerta=" + alerta +
				", data=" + data +
				'}';
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BiletP biletP = (BiletP) o;
		return idbiletp == biletP.idbiletp &&
				activ == biletP.activ &&
				Double.compare(biletP.pret, pret) == 0 &&
				alerta == biletP.alerta &&
				Objects.equals(locatie, biletP.locatie) &&
				Objects.equals(data, biletP.data);
	}
	
	@Override
	public int hashCode() {
		
		return Objects.hash(idbiletp, locatie, activ, pret, alerta, data);
	}
}
