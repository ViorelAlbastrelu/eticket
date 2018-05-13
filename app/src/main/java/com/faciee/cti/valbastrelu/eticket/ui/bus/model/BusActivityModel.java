package com.faciee.cti.valbastrelu.eticket.ui.bus.model;

import com.faciee.cti.valbastrelu.eticket.ui.model.Bilet;
import com.faciee.cti.valbastrelu.eticket.ui.model.Istoric;
import com.faciee.cti.valbastrelu.eticket.ui.model.TransportType;
import com.faciee.cti.valbastrelu.eticket.ui.model.Traseu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BusActivityModel extends AbstractActivityModel {
	
	private Bilet biletActiv = null;
	private ArrayList<Bilet> listaBilete;
	private ArrayList<Traseu> listaTrasee;
	private ArrayList<Istoric> listaIstorce;
	private SimpleDateFormat formatterDate = new SimpleDateFormat("dd-MMM-yyyy");
	private SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");
	
	public BusActivityModel() {
		listaBilete = new ArrayList<>();
		listaTrasee = new ArrayList<>();
		listaIstorce = new ArrayList<>();
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
			listaTrasee.add(new Traseu(39, formatterTime.parse("11:12"), TransportType.TRAM));
			listaTrasee.add(new Traseu(10, formatterTime.parse("11:32"), TransportType.BUS));
			listaTrasee.add(new Traseu(102, formatterTime.parse("11:45"), TransportType.TBUS));
			listaTrasee.add(new Traseu(7, formatterTime.parse("12:02"), TransportType.TRAM));
			listaTrasee.add(new Traseu(39, formatterTime.parse("12:12"), TransportType.TRAM));
			listaTrasee.add(new Traseu(10, formatterTime.parse("12:32"), TransportType.BUS));
			listaTrasee.add(new Traseu(102, formatterTime.parse("12:45"), TransportType.TBUS));
			listaTrasee.add(new Traseu(7, formatterTime.parse("13:02"), TransportType.TRAM));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return listaTrasee;
	}
	
	public ArrayList<Istoric> getListaIstorice(){
		try {
			listaIstorce.add(new Istoric(formatterDate.parse("12-JAN-2018"),TransportType.BUS,34, -2.00));
			listaIstorce.add(new Istoric(formatterDate.parse("13-JAN-2018"),TransportType.TBUS,102, -2.00));
			listaIstorce.add(new Istoric(formatterDate.parse("16-JAN-2018"),TransportType.TRAM,7, -2.00));
			listaIstorce.add(new Istoric(formatterDate.parse("22-JAN-2018"),TransportType.BUS,7, -2.00));
			listaIstorce.add(new Istoric(formatterDate.parse("23-JAN-2018"),TransportType.CAR,7, -1.00));
			listaIstorce.add(new Istoric(formatterDate.parse("24-JAN-2018"),TransportType.NONE,0, +15.00));
			listaIstorce.add(new Istoric(formatterDate.parse("30-JAN-2018"),TransportType.TBUS,104, -2.00));
			listaIstorce.add(new Istoric(formatterDate.parse("02-FEB-2018"),TransportType.BUS,24, -2.00));
			listaIstorce.add(new Istoric(formatterDate.parse("10-FEB-2018"),TransportType.CAR,7, -1.00));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return listaIstorce;
	}
}
