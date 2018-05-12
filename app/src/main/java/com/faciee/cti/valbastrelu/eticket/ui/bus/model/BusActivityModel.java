package com.faciee.cti.valbastrelu.eticket.ui.bus.model;

import com.faciee.cti.valbastrelu.eticket.ui.model.Bilet;
import com.faciee.cti.valbastrelu.eticket.ui.model.TransportType;
import com.faciee.cti.valbastrelu.eticket.ui.model.Traseu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BusActivityModel extends AbstractActivityModel {
	
	private Bilet biletActiv = null;
	private ArrayList<Bilet> listaBilete;
	private ArrayList<Traseu> listaTrasee;
	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
	
	public BusActivityModel() {
		listaBilete = new ArrayList<>();
		listaTrasee = new ArrayList<>();
	}
	
	public void setBiletActiv(Bilet biletActiv) {
		this.biletActiv = biletActiv;
	}
	
	
	public ArrayList<Bilet> getListaBilete(){
		//TODO get from web with Jsoup library
		listaBilete.add(new Bilet(102, true, 2,2));
		listaBilete.add(new Bilet(7, false, 2,2));
		listaBilete.add(new Bilet(44, false, 2,2));
		return listaBilete;
	}
	
	public ArrayList<Traseu> getListaTrasee(){
		try {
			listaTrasee.add(new Traseu(39, formatter.parse("11:12"), TransportType.TRAM));
			listaTrasee.add(new Traseu(10, formatter.parse("11:32"), TransportType.BUS));
			listaTrasee.add(new Traseu(102, formatter.parse("11:45"), TransportType.TBUS));
			listaTrasee.add(new Traseu(7, formatter.parse("12:02"), TransportType.TRAM));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return listaTrasee;
	}
}
