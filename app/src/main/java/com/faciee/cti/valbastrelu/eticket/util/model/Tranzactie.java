package com.faciee.cti.valbastrelu.eticket.util.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;
@Entity
public class Tranzactie {
	@PrimaryKey(autoGenerate = true)
	private int idtranz;
//	@ColumnInfo(name = "transport_type")
	private TransportType transportType;
//	@ColumnInfo(name = "data")
	private Date data;
	@ColumnInfo(name = "nr_traseu")
	private int nrTraseu;
	@ColumnInfo(name = "suma")
	private double suma;
	
	public Tranzactie(Date data, TransportType transportType, int nrTraseu, double suma) {
		this.data = data;
		this.transportType = transportType;
		this.nrTraseu = nrTraseu;
		this.suma = suma;
	}
	public int getIdtranz() {
		return idtranz;
	}
	
	public void setIdtranz(int idtranz) {
		this.idtranz = idtranz;
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
