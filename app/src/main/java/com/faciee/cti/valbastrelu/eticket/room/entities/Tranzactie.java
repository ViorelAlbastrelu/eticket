package com.faciee.cti.valbastrelu.eticket.room.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.faciee.cti.valbastrelu.eticket.room.converter.DateConverter;
import com.faciee.cti.valbastrelu.eticket.room.converter.TrasportTypeConverter;
import com.faciee.cti.valbastrelu.eticket.ui.common.TransportType;

import java.util.Date;
@Entity
public class Tranzactie {
	
	@PrimaryKey private long idtranz;
	@ColumnInfo private int nrTraseu;
	@ColumnInfo private double suma;
	@TypeConverters(TrasportTypeConverter.class) private TransportType transportType;
	@TypeConverters(DateConverter.class) private Date data;
	
	public Tranzactie(long idtranz, Date data, TransportType transportType, int nrTraseu, double suma) {
		this.idtranz = idtranz;
		this.data = data;
		this.transportType = transportType;
		this.nrTraseu = nrTraseu;
		this.suma = suma;
	}
	public long getIdtranz() {
		return idtranz;
	}
	
	public void setIdtranz(long idtranz) {
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
