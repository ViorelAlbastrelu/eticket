package com.faciee.cti.valbastrelu.eticket.ui.bus;

import com.faciee.cti.valbastrelu.eticket.ui.model.Bilet;

import java.util.List;

public class BusActivityModel {
	private Bilet biletActiv = null;
	private List<Bilet> listaBilete;
	
	public BusActivityModel() {
	}
	
	public void setBiletActiv(Bilet biletActiv) {
		this.biletActiv = biletActiv;
	}
}
