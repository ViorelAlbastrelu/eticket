package com.faciee.cti.valbastrelu.eticket.util.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Statie {
	
	@PrimaryKey(autoGenerate = true) private int idstatie;
	@ColumnInfo private String numeStatie;
	@ColumnInfo private String sens;
	
	public Statie(String numeStatie, String sens) {
		this.numeStatie = numeStatie;
		this.sens = sens;
	}
	
	public String getNumeStatie() {
		return numeStatie;
	}
	
	public void setNumeStatie(String numeStatie) {
		this.numeStatie = numeStatie;
	}
	
	public String getSens() {
		return sens;
	}
	
	public void setSens(String sens) {
		this.sens = sens;
	}
	
	@Override
	public String toString() {
		return "Statie{" +
				"numeStatie='" + numeStatie + '\'' +
				", sens='" + sens + '\'' +
				"}\n";
	}
}
