package com.faciee.cti.valbastrelu.eticket.room.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Statie {
	
	@PrimaryKey
	private int idstatie;
	private String numeStatie;
	private String sens;
	private int traseu;
	
	@Ignore
	private static int idGenerator = 0;
	
	public Statie(int traseu, String numeStatie, String sens) {
		idstatie = idGenerator++;
		this.traseu = traseu;
		this.numeStatie = numeStatie;
		this.sens = sens;
	}
	
	public int getIdstatie() {
		return idstatie;
	}
	
	public void setIdstatie(int idstatie) {
		this.idstatie = idstatie;
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
	
	public int getTraseu() {
		return traseu;
	}
	
	public void setTraseu(int traseu) {
		this.traseu = traseu;
	}
	
	@Override
	public String toString() {
		return "Statie{" +
				"idstatie=" + idstatie +
				", numeStatie='" + numeStatie + '\'' +
				", sens='" + sens + '\'' +
				", traseu=" + traseu +
				'}';
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Statie statie = (Statie) o;
		return idstatie == statie.idstatie &&
				traseu == statie.traseu &&
				Objects.equals(numeStatie, statie.numeStatie) &&
				Objects.equals(sens, statie.sens);
	}
	
	@Override
	public int hashCode() {
		
		return Objects.hash(idstatie, numeStatie, sens, traseu);
	}
}
