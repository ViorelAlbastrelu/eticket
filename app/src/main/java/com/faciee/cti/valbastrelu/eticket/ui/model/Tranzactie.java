package com.faciee.cti.valbastrelu.eticket.ui.model;

import java.util.Date;

public class Istoric {
	private Date data;
	private TransportType transportType;
	private int nrTraseu;
	private double pret;
	
	public Istoric(Date data, TransportType transportType, int nrTraseu, double pret) {
		this.data = data;
		this.transportType = transportType;
		this.nrTraseu = nrTraseu;
		this.pret = pret;
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
	
	public double getPret() {
		return pret;
	}
	
	public void setPret(double pret) {
		this.pret = pret;
	}
}
