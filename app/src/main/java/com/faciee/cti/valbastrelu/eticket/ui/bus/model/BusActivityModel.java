package com.faciee.cti.valbastrelu.eticket.ui.bus.model;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.faciee.cti.valbastrelu.eticket.ui.common.AbstractActivityModel;
import com.faciee.cti.valbastrelu.eticket.ui.model.Bilet;
import com.faciee.cti.valbastrelu.eticket.ui.model.Statie;
import com.faciee.cti.valbastrelu.eticket.ui.model.Tranzactie;
import com.faciee.cti.valbastrelu.eticket.ui.model.TransportType;
import com.faciee.cti.valbastrelu.eticket.ui.model.Traseu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BusActivityModel extends AbstractActivityModel {
	private static final String TAG = "BusActivityModel";
	
	private static String URL_STATII = "https://transurb-galati.com/statii%s/";
	
	private MutableLiveData<List<Bilet>> bilete;
	private MutableLiveData<List<Traseu>> trasee;
	private MutableLiveData<List<Statie>> statiiLiveData;
	private MutableLiveData<List<Tranzactie>> tranzactii;
	
	private Bilet biletActiv = null;
	private ArrayList<Bilet> listaBilete;
	private ArrayList<Traseu> listaTrasee;
	private ArrayList<Tranzactie> listaIstorce;
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
	
	public LiveData<List<Bilet>> getLiveDataBilete(){
		if (bilete == null){
			bilete = new MutableLiveData<>();
			loadBilete();
		}
		return bilete;
	}
	
	private void loadBilete() {
		
	}
	
	public LiveData<List<Traseu>> getLiveDataTrasee(){
		if (trasee == null){
			trasee = new MutableLiveData<>();
			loadTrasee();
		}
		return trasee;
	}
	
	private void loadTrasee() {
	}
	
	public LiveData<List<Statie>> getLiveDataStatii(int nrTraseu){
		if (statiiLiveData == null){
			statiiLiveData = new MutableLiveData<>();
			loadStatii(nrTraseu);
		}
		return statiiLiveData;
	}
	
	private void loadStatii(int numarTraseu) {
		@SuppressLint("DefaultLocale")
		String url = numarTraseu == 0 ?
				String.format(URL_STATII, ""):
				String.format(URL_STATII, "-traseu-" + numarTraseu);
		List<Statie> statieList = new ArrayList<>();
		try {
			Document document = Jsoup.connect(url).get();
			Elements rows = document.select("td");
			for (int i = 2; i < rows.size(); i += 2) {
				statieList.add(new Statie(rows.get(i).text(), rows.get(i + 1).text()));
			}
		} catch (IOException e) {
			Log.e(TAG, "loadStatii: " + e.getMessage(), null);
		}
		statiiLiveData.setValue(statieList);
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
	
	public ArrayList<Tranzactie> getListaIstorice(){
		try {
			listaIstorce.add(new Tranzactie(formatterDate.parse("12-JAN-2018"),TransportType.BUS,34, -2.00));
			listaIstorce.add(new Tranzactie(formatterDate.parse("13-JAN-2018"),TransportType.TBUS,102, -2.00));
			listaIstorce.add(new Tranzactie(formatterDate.parse("16-JAN-2018"),TransportType.TRAM,7, -2.00));
			listaIstorce.add(new Tranzactie(formatterDate.parse("22-JAN-2018"),TransportType.BUS,7, -2.00));
			listaIstorce.add(new Tranzactie(formatterDate.parse("23-JAN-2018"),TransportType.PARKING,7, -1.00));
			listaIstorce.add(new Tranzactie(formatterDate.parse("24-JAN-2018"),TransportType.NONE,0, +15.00));
			listaIstorce.add(new Tranzactie(formatterDate.parse("30-JAN-2018"),TransportType.TBUS,104, -2.00));
			listaIstorce.add(new Tranzactie(formatterDate.parse("02-FEB-2018"),TransportType.BUS,24, -2.00));
			listaIstorce.add(new Tranzactie(formatterDate.parse("10-FEB-2018"),TransportType.PARKING,7, -1.00));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return listaIstorce;
	}
	
	@Override
	protected void onCleared() {
		super.onCleared();
		Log.d(TAG, "onCleared: called");
	}
	
	//	public ArrayList getListaStatii(){
////		listaStatii.add("Micro 19-Cinema Dacia");
////		listaStatii.add("Otelarilor");
////		listaStatii.add("Bloc D19");
////		listaStatii.add("Sala Sporturilor");
////		listaStatii.add("Flora");
////		listaStatii.add("Stadionul Otelul");
////		listaStatii.add("Ghe. Doja");
////		listaStatii.add("Piata Energiei T");
////		listaStatii.add("Liceul 9");
////		listaStatii.add("Piata Energiei R");
////		listaStatii.add("ICFrimu");
////		listaStatii.add("George Cosbuc");
////		listaStatii.add("Posta Veche");
////		listaStatii.add("Baia Comunala");
////		listaStatii.add("Piata Centrala");
////		return listaStatii;
//	}
}
