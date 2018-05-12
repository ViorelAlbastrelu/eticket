package com.faciee.cti.valbastrelu.eticket.ui.model;

import java.util.Date;

public class Istoric {
	private Date data;
	private TransportType transportType;
	private int nrTraseu;
	private int pret;
	
	public Istoric(Date data, TransportType transportType, int nrTraseu, int pret) {
		this.data = data;
		this.transportType = transportType;
		this.nrTraseu = nrTraseu;
		this.pret = pret;
	}
	
	public Date getData() {
		return data;
	}
	
	public String getDataForIstoricView(){
		return data.getDay() + " " + data.getMonth();
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
	
	public int getPret() {
		return pret;
	}
	
	public void setPret(int pret) {
		this.pret = pret;
	}
}
