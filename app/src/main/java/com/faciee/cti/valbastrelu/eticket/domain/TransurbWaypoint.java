package com.faciee.cti.valbastrelu.eticket.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class TransurbWaypoint {
    @SerializedName("tur")
    @Expose
    private List<String> tur = null;
    @SerializedName("retur")
    @Expose
    private List<String> retur = null;

    public List<String> getTur() {
        return tur;
    }

    public void setTur(List<String> tur) {
        this.tur = tur;
    }

    public List<String> getRetur() {
        return retur;
    }

    public void setRetur(List<String> retur) {
        this.retur = retur;
    }
}
