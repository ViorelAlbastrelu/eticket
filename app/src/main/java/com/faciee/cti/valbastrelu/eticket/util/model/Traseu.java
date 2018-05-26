package com.faciee.cti.valbastrelu.eticket.util.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

@Entity
public class Traseu {
	
	@PrimaryKey private int nrTraseu;
	@ColumnInfo private Date ora;
	@TypeConverters(TransportType.class) private TransportType transportType;
	
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
