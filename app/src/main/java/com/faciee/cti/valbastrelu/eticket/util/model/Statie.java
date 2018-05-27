package com.faciee.cti.valbastrelu.eticket.util.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Traseu.class,
									parentColumns = "nrTraseu",
									childColumns = "nrTraseu",
									onDelete = CASCADE))
public class Statie {
	
	@PrimaryKey(autoGenerate = true) public int idstatie;
	private String numeStatie;
	private String sens;
	private int nrTraseu;
	
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
	
	public int getNrTraseu() {
		return nrTraseu;
	}
	
	public void setNrTraseu(int nrTraseu) {
		this.nrTraseu = nrTraseu;
	}
	
	@Override
	public String toString() {
		return "Statie{" +
				"idstatie=" + idstatie +
				", numeStatie='" + numeStatie + '\'' +
				", sens='" + sens + '\'' +
				", nrTraseu=" + nrTraseu +
				'}';
	}
}
