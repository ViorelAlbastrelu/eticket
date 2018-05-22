package com.faciee.cti.valbastrelu.eticket.util.model;

public class Statie {
	
	private String numeStatie;
	private String sens;
	
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
