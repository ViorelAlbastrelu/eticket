package com.faciee.cti.valbastrelu.eticket.ui.bus.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.Nullable;

import com.faciee.cti.valbastrelu.eticket.room.converter.DateConverter;
import com.faciee.cti.valbastrelu.eticket.room.converter.TrasportTypeConverter;

import java.util.Date;
import java.util.Objects;

@Entity
public class Traseu {
	
	@PrimaryKey private int nrTraseu;
	@TypeConverters(DateConverter.class) private Date ora;
	@TypeConverters(TrasportTypeConverter.class) private TransportType transportType;
	
	public Traseu(int nrTraseu, @Nullable Date ora, TransportType transportType) {
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
	
	public String getOraFormat() {
		return ora.getHours() + ":" + ora.getMinutes();
	}
	
	public Date getOra() {
		return ora;
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
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Traseu traseu = (Traseu) o;
		return nrTraseu == traseu.nrTraseu &&
				transportType == traseu.transportType;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nrTraseu, transportType);
	}
}
