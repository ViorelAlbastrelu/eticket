package com.faciee.cti.valbastrelu.eticket.ui.model;

import android.support.annotation.DrawableRes;

import com.faciee.cti.valbastrelu.eticket.R;

public enum TransportType {
	BUS("autobuz", R.drawable.ic_bus),
	TBUS("troleibuz", R.drawable.ic_tbus),
	TRAM("tramvai", R.drawable.ic_tram),
	CAR("autoturism", R.drawable.ic_auto);
	
	private final String typeName;
	private final int transportTypeIcon;
	
	TransportType(String name, @DrawableRes int transportTypeIcon) {
		this.typeName = name;
		this.transportTypeIcon = transportTypeIcon;
	}
	
	public String getTypeName() {
		return typeName;
	}
	
	public int getTransportTypeIcon() {
		return transportTypeIcon;
	}
}
