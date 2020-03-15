package com.faciee.cti.valbastrelu.eticket.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Schedules {

    @SerializedName("bus")
    @Expose private List<Vehicle> buses = null;
    @SerializedName("tbus")
    @Expose private List<Vehicle> trolleyBuses = null;
    @SerializedName("tram")
    @Expose private List<Vehicle> tramway = null;
    @SerializedName("estbus")
    @Expose private List<Vehicle> estivalBuses = null;

    public List<Vehicle> getBuses() {
        return buses;
    }

    public void setBuses(List<Vehicle> buses) {
        this.buses = buses;
    }

    public List<Vehicle> getTrolleyBuses() {
        return trolleyBuses;
    }

    public void setTrolleyBuses(List<Vehicle> trolleyBuses) {
        this.trolleyBuses = trolleyBuses;
    }

    public List<Vehicle> getTramway() {
        return tramway;
    }

    public void setTramway(List<Vehicle> tramway) {
        this.tramway = tramway;
    }

    public List<Vehicle> getEstivalBuses() {
        return estivalBuses;
    }

    public void setEstivalBuses(List<Vehicle> estivalBuses) {
        this.estivalBuses = estivalBuses;
    }
}
