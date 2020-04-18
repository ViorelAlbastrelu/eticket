package com.faciee.cti.valbastrelu.eticket.util;

import com.faciee.cti.valbastrelu.eticket.room.entities.Ticket;
import com.faciee.cti.valbastrelu.eticket.room.entities.Route;
import com.faciee.cti.valbastrelu.eticket.room.entities.Transaction;
import com.faciee.cti.valbastrelu.eticket.ui.common.TransportType;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DummyData {

    private static final SimpleDateFormat formatterDate = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
    private final SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm", Locale.ENGLISH);

    public static List<Ticket> loadBilete() {
        List<Ticket> listaBilete = new ArrayList<>();
        listaBilete.add(new Ticket(102, true, 2,new BigDecimal(2)));
        listaBilete.add(new Ticket(7, false, 2, new BigDecimal(2)));
        listaBilete.add(new Ticket(44, false, 2,new BigDecimal(2)));
        return listaBilete;
    }

    public static List<Route> loadTrasee() {
        List<Route> listaTrasee = new ArrayList<>();
        listaTrasee.add(new Route(9, null, TransportType.BUS));
        listaTrasee.add(new Route(10, null, TransportType.BUS));
        listaTrasee.add(new Route(11, null, TransportType.BUS));
        listaTrasee.add(new Route(12, null, TransportType.BUS));
        listaTrasee.add(new Route(13, null, TransportType.BUS));
        listaTrasee.add(new Route(14, null, TransportType.BUS));
        listaTrasee.add(new Route(15, null, TransportType.BUS));
        listaTrasee.add(new Route(16, null, TransportType.BUS));
        listaTrasee.add(new Route(17, null, TransportType.BUS));
        listaTrasee.add(new Route(18, null, TransportType.BUS));
        listaTrasee.add(new Route(19, null, TransportType.BUS));
        listaTrasee.add(new Route(20, null, TransportType.BUS));
        listaTrasee.add(new Route(22, null, TransportType.BUS));
        listaTrasee.add(new Route(23, null, TransportType.BUS));
        listaTrasee.add(new Route(24, null, TransportType.BUS));
        listaTrasee.add(new Route(25, null, TransportType.BUS));
        listaTrasee.add(new Route(26, null, TransportType.BUS));
        listaTrasee.add(new Route(27, null, TransportType.BUS));
        listaTrasee.add(new Route(28, null, TransportType.BUS));
        listaTrasee.add(new Route(29, null, TransportType.BUS));
        listaTrasee.add(new Route(30, null, TransportType.BUS));
        listaTrasee.add(new Route(31, null, TransportType.BUS));
        listaTrasee.add(new Route(34, null, TransportType.BUS));
        listaTrasee.add(new Route(35, null, TransportType.BUS));
        listaTrasee.add(new Route(36, null, TransportType.BUS));
        listaTrasee.add(new Route(105, null, TransportType.BUS));
        return listaTrasee;
    }

    public static List<Transaction> loadIstorice() {
        List<Transaction> listaIstorice = new ArrayList<>();
        try {
            listaIstorice.add(new Transaction(1L, formatterDate.parse("12-JAN-2018"), TransportType.BUS, 2,new BigDecimal(-2.00)));
            listaIstorice.add(new Transaction(2L, formatterDate.parse("13-JAN-2018"), TransportType.TBUS, 2, new BigDecimal(-2.00)));
            listaIstorice.add(new Transaction(3L, formatterDate.parse("16-JAN-2018"), TransportType.TRAM, 2, new BigDecimal(-2.00)));
            listaIstorice.add(new Transaction(4L, formatterDate.parse("22-JAN-2018"), TransportType.BUS, 2, new BigDecimal(-2.00)));
            listaIstorice.add(new Transaction(5L, formatterDate.parse("23-JAN-2018"), TransportType.PARKING, 2, new BigDecimal(-1.00)));
            listaIstorice.add(new Transaction(6L, formatterDate.parse("24-JAN-2018"), TransportType.NONE, 2, new BigDecimal(+15.00)));
            listaIstorice.add(new Transaction(7L, formatterDate.parse("30-JAN-2018"), TransportType.TBUS, 2, new BigDecimal(-2.00)));
            listaIstorice.add(new Transaction(8L, formatterDate.parse("02-FEB-2018"), TransportType.BUS, 2, new BigDecimal(-2.00)));
            listaIstorice.add(new Transaction(9L, formatterDate.parse("10-FEB-2018"), TransportType.PARKING, 2, new BigDecimal(-1.00)));
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
