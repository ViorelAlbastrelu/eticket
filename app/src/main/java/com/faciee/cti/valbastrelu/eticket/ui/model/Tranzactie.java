package com.faciee.cti.valbastrelu.eticket.ui.model;

import java.util.Date;

public class Tranzactie {
	private Date data;
	private TransportType transportType;
	private int nrTraseu;
	private double suma;
	
	public Tranzactie(Date data, TransportType transportType, int nrTraseu, double suma) {
		this.data = data;
		this.transportType = transportType;
		this.nrTraseu = nrTraseu;
		this.suma = suma;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public TransportType getTransportType() {
		return transportType;
	}
	
	public void setTransportType(TransportType transportType) {
		this.transportType = transportType;
	}
	
	public int getNrTraseu() {
		return nrTraseu;
	}
	
	public void setNrTraseu(int nrTraseu) {
		this.nrTraseu = nrTraseu;
	}
	
	public double getSuma() {
		return suma;
	}
	
	public void setSuma(double suma) {
		this.suma = suma;
	}
}
