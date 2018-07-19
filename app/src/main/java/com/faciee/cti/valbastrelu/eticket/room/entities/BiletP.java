package com.faciee.cti.valbastrelu.eticket.room.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Calendar;
import java.util.Objects;

@Entity
public class BiletP {
	
	@PrimaryKey private long idbiletp;
	@ColumnInfo private String locatie;
	@ColumnInfo private boolean activ;
	@ColumnInfo private double pret;
	@ColumnInfo private boolean alerta;
	
	public BiletP(String locatie, boolean activ, double pret, boolean alerta) {
		this.idbiletp = System.currentTimeMillis();;
		this.locatie = locatie;
		this.activ = activ;
		this.pret = pret;
		this.alerta = alerta;
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
	
	@Override
	public String toString() {
		return "BiletP{" +
				"idbiletp=" + idbiletp +
				", locatie='" + locatie + '\'' +
				", activ=" + activ +
				", pret=" + pret +
				", alerta=" + alerta +
				'}';
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BiletP biletP = (BiletP) o;
		return idbiletp == biletP.idbiletp &&
				activ == biletP.activ &&
				pret == biletP.pret &&
				alerta == biletP.alerta &&
				Objects.equals(locatie, biletP.locatie);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idbiletp, locatie, activ, pret, alerta);
	}
}
