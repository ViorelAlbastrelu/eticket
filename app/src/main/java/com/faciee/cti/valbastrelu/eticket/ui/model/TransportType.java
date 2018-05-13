package com.faciee.cti.valbastrelu.eticket.ui.model;

import android.support.annotation.DrawableRes;

import com.faciee.cti.valbastrelu.eticket.R;

public enum TransportType {
	BUS("Autobuz", R.drawable.ic_bus),
	TBUS("Troleibuz", R.drawable.ic_tbus),
	TRAM("Tramvai", R.drawable.ic_tram),
	CAR("Autoturism", R.drawable.ic_auto),
	NONE("Undefined", R.drawable.default_icon);
	
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
