package com.faciee.cti.valbastrelu.eticket.ui.model;

import java.util.Calendar;
import java.util.Date;

public class Bilet {
	private int traseu;
	private boolean activ;
	private int calatorii;
	private int pret;
	private Date data;
	
	public Bilet(int traseu, boolean activ, int pret, int calatorii) {
		this.traseu = traseu;
		this.activ = activ;
		this.calatorii = calatorii;
		this.pret = pret;
//		this.data = Calendar.getInstance().getTime();
	}
	
	public int getTraseu() {
		return traseu;
	}
	
	public void setTraseu(int traseu) {
		this.traseu = traseu;
	}
	
	public boolean isActiv() {
		return activ;
	}
	
	public void setActiv(boolean activ) {
		this.activ = activ;
	}
	
	public int getCalatorii() {
		return calatorii;
	}
	
	public void setCalatorii(int calatorii) {
		this.calatorii = calatorii;
	}
	
	public int getPret() {
		return pret;
	}
	
	public void setPret(int pret) {
		this.pret = pret;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
}
