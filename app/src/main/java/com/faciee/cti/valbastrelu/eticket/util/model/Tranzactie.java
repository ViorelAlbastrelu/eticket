package com.faciee.cti.valbastrelu.eticket.util.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.faciee.cti.valbastrelu.eticket.util.room.DateConverter;
import com.faciee.cti.valbastrelu.eticket.util.room.TrasportTypeConverter;

import java.util.Date;
@Entity
public class Tranzactie {
	
	@PrimaryKey(autoGenerate = true) private int idtranz;
	@ColumnInfo private int nrTraseu;
	@ColumnInfo private double suma;
	@TypeConverters(TrasportTypeConverter.class) private TransportType transportType;
	@TypeConverters(DateConverter.class) private Date data;
	
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
