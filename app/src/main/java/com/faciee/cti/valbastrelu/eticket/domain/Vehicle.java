package com.faciee.cti.valbastrelu.eticket.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Vehicle {

    @SerializedName("traseu")
    @Expose
    private Integer traseu;
    @SerializedName("strazi")
    @Expose
    private List<TransurbWaypoint> streets = null;
    @SerializedName("statii")
    @Expose
    private List<TransurbWaypoint> stations = null;

    public Integer getTraseu() {
        return traseu;
    }

    public void setTraseu(Integer traseu) {
        this.traseu = traseu;
    }

    public List<TransurbWaypoint> getStreets() {
        return streets;
    }

    public void setStreets(List<TransurbWaypoint> streets) {
        this.streets = streets;
    }

    public List<TransurbWaypoint> getStations() {
        return stations;
    }

    public void setStations(List<TransurbWaypoint> stations) {
        this.stations = stations;
    }

}
