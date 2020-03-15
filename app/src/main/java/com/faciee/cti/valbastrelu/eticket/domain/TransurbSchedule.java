package com.faciee.cti.valbastrelu.eticket.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransurbSchedule {

    @SerializedName("itinerariu")
    @Expose
    private Schedules schedules;

    public Schedules getSchedules() {
        return schedules;
    }

    public void setSchedules(Schedules schedules) {
        this.schedules = schedules;
    }

}