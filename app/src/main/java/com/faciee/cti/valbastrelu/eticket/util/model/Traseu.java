package com.faciee.cti.valbastrelu.eticket.util.model;

import java.util.Date;

public class Traseu {
	private int nrTraseu;
	private Date ora;
	private TransportType transportType;
	
	public Traseu() {
	}
	
	public Traseu(int nrTraseu, Date ora, TransportType transportType) {
		this.nrTraseu = nrTraseu;
		this.ora = ora;
		this.transportType = transportType;
	}
	
	public int getNrTraseu() {
		return nrTraseu;
	}
	
	public void setNrTraseu(int nrTraseu) {
		this.nrTraseu = nrTraseu;
	}
	
	public String getOra() {
		return ora.getHours() + ":" + ora.getMinutes();
	}
	
	public void setOra(Date ora) {
		this.ora = ora;
	}
	
	public TransportType getTransportType() {
		return transportType;
	}
	
	public void setTransportType(TransportType transportType) {
		this.transportType = transportType;
	}
}