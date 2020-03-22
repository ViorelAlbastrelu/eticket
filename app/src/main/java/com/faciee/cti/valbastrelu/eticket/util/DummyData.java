package com.faciee.cti.valbastrelu.eticket.util;

import com.faciee.cti.valbastrelu.eticket.room.entities.Bilet;
import com.faciee.cti.valbastrelu.eticket.room.entities.Tranzactie;
import com.faciee.cti.valbastrelu.eticket.room.entities.Traseu;
import com.faciee.cti.valbastrelu.eticket.ui.common.TransportType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DummyData {

    private static final SimpleDateFormat formatterDate = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
    private final SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm", Locale.ENGLISH);

    public static List<Bilet> loadBilete() {
        List<Bilet> listaBilete = new ArrayList<>();
        listaBilete.add(new Bilet(102, true, 2, 2));
        listaBilete.add(new Bilet(7, false, 2, 2));
        listaBilete.add(new Bilet(44, false, 2, 2));
        return listaBilete;
    }

    public static List<Traseu> loadTrasee() {
        List<Traseu> listaTrasee = new ArrayList<>();
        listaTrasee.add(new Traseu(9, null, TransportType.BUS));
        listaTrasee.add(new Traseu(10, null, TransportType.BUS));
        listaTrasee.add(new Traseu(11, null, TransportType.BUS));
        listaTrasee.add(new Traseu(12, null, TransportType.BUS));
        listaTrasee.add(new Traseu(13, null, TransportType.BUS));
        listaTrasee.add(new Traseu(14, null, TransportType.BUS));
        listaTrasee.add(new Traseu(15, null, TransportType.BUS));
        listaTrasee.add(new Traseu(16, null, TransportType.BUS));
        listaTrasee.add(new Traseu(17, null, TransportType.BUS));
        listaTrasee.add(new Traseu(18, null, TransportType.BUS));
        listaTrasee.add(new Traseu(19, null, TransportType.BUS));
        listaTrasee.add(new Traseu(20, null, TransportType.BUS));
        listaTrasee.add(new Traseu(22, null, TransportType.BUS));
        listaTrasee.add(new Traseu(23, null, TransportType.BUS));
        listaTrasee.add(new Traseu(24, null, TransportType.BUS));
        listaTrasee.add(new Traseu(25, null, TransportType.BUS));
        listaTrasee.add(new Traseu(26, null, TransportType.BUS));
        listaTrasee.add(new Traseu(27, null, TransportType.BUS));
        listaTrasee.add(new Traseu(28, null, TransportType.BUS));
        listaTrasee.add(new Traseu(29, null, TransportType.BUS));
        listaTrasee.add(new Traseu(30, null, TransportType.BUS));
        listaTrasee.add(new Traseu(31, null, TransportType.BUS));
        listaTrasee.add(new Traseu(34, null, TransportType.BUS));
        listaTrasee.add(new Traseu(35, null, TransportType.BUS));
        listaTrasee.add(new Traseu(36, null, TransportType.BUS));
        listaTrasee.add(new Traseu(105, null, TransportType.BUS));
        return listaTrasee;
    }

    public static List<Tranzactie> loadIstorice() {
        List<Tranzactie> listaIstorice = new ArrayList<>();
        try {
            listaIstorice.add(new Tranzactie(1L, formatterDate.parse("12-JAN-2018"), TransportType.BUS, 2, -2.00));
            listaIstorice.add(new Tranzactie(2L, formatterDate.parse("13-JAN-2018"), TransportType.TBUS, 2, -2.00));
            listaIstorice.add(new Tranzactie(3L, formatterDate.parse("16-JAN-2018"), TransportType.TRAM, 2, -2.00));
            listaIstorice.add(new Tranzactie(4L, formatterDate.parse("22-JAN-2018"), TransportType.BUS, 2, -2.00));
            listaIstorice.add(new Tranzactie(5L, formatterDate.parse("23-JAN-2018"), TransportType.PARKING, 2, -1.00));
            listaIstorice.add(new Tranzactie(6L, formatterDate.parse("24-JAN-2018"), TransportType.NONE, 2, +15.00));
            listaIstorice.add(new Tranzactie(7L, formatterDate.parse("30-JAN-2018"), TransportType.TBUS, 2, -2.00));
            listaIstorice.add(new Tranzactie(8L, formatterDate.parse("02-FEB-2018"), TransportType.BUS, 2, -2.00));
            listaIstorice.add(new Tranzactie(9L, formatterDate.parse("10-FEB-2018"), TransportType.PARKING, 2, -1.00));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listaIstorice;
    }

    public List<String> loadStatiiTest(int nrTraseu) {
        List<String> listaStatii = new ArrayList<>();
        listaStatii.add("Micro 19-Cinema Dacia");
        listaStatii.add("Otelarilor");
        listaStatii.add("Bloc D19");
        listaStatii.add("Sala Sporturilor");
        listaStatii.add("Flora");
        listaStatii.add("Stadionul Otelul");
        listaStatii.add("Ghe. Doja");
        listaStatii.add("Piata Energiei T");
        listaStatii.add("Liceul 9");
        listaStatii.add("Piata Energiei R");
        listaStatii.add("ICFrimu");
        listaStatii.add("George Cosbuc");
        listaStatii.add("Posta Veche");
        listaStatii.add("Baia Comunala");
        listaStatii.add("Piata Centrala");
        return listaStatii;
    }
}
