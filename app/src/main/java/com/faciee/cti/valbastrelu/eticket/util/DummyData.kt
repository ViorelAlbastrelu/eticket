package com.faciee.cti.valbastrelu.eticket.util

import com.faciee.cti.valbastrelu.eticket.room.entities.Route
import com.faciee.cti.valbastrelu.eticket.room.entities.Ticket
import com.faciee.cti.valbastrelu.eticket.room.entities.TicketParking
import com.faciee.cti.valbastrelu.eticket.room.entities.Transaction
import com.faciee.cti.valbastrelu.eticket.ui.common.TransportType
import java.math.BigDecimal
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DummyData {

	private val formatterTime = SimpleDateFormat("HH:mm", Locale.ENGLISH)
	fun loadStatiiTest(nrTraseu: Int): List<String> {
		val listaStatii: MutableList<String> = ArrayList()
		listaStatii.add("Micro 19-Cinema Dacia")
		listaStatii.add("Otelarilor")
		listaStatii.add("Bloc D19")
		listaStatii.add("Sala Sporturilor")
		listaStatii.add("Flora")
		listaStatii.add("Stadionul Otelul")
		listaStatii.add("Ghe. Doja")
		listaStatii.add("Piata Energiei T")
		listaStatii.add("Liceul 9")
		listaStatii.add("Piata Energiei R")
		listaStatii.add("ICFrimu")
		listaStatii.add("George Cosbuc")
		listaStatii.add("Posta Veche")
		listaStatii.add("Baia Comunala")
		listaStatii.add("Piata Centrala")
		return listaStatii
	}

	private val formatterDate = SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH)
	fun loadBilete(): List<Ticket> {
		val listaBilete: MutableList<Ticket> = ArrayList()
		listaBilete.add(Ticket(1, 102, true, 2, BigDecimal(2)))
		listaBilete.add(Ticket(2, 7, false, 2, BigDecimal(2)))
		listaBilete.add(Ticket(3, 44, false, 2, BigDecimal(2)))
		return listaBilete
	}

	fun loadParkingTickets(): List<TicketParking> {
		return arrayListOf<TicketParking>().apply {
			add(TicketParking(1,"Mazepa", true, BigDecimal.TEN, true))
			add(TicketParking(2,"Tiglina", false, BigDecimal.TEN, false))
			add(TicketParking(3,"Centru", false, BigDecimal.TEN, false))
		}
	}

	fun loadTrasee(): List<Route> {
		val listaTrasee: MutableList<Route> = ArrayList()
//		listaTrasee.add(Route(9, null, TransportType.BUS))
//		listaTrasee.add(Route(10, null, TransportType.BUS))
//		listaTrasee.add(Route(11, null, TransportType.BUS))
//		listaTrasee.add(Route(12, null, TransportType.BUS))
//		listaTrasee.add(Route(13, null, TransportType.BUS))
//		listaTrasee.add(Route(14, null, TransportType.BUS))
//		listaTrasee.add(Route(15, null, TransportType.BUS))
//		listaTrasee.add(Route(16, null, TransportType.BUS))
//		listaTrasee.add(Route(17, null, TransportType.BUS))
//		listaTrasee.add(Route(18, null, TransportType.BUS))
//		listaTrasee.add(Route(19, null, TransportType.BUS))
//		listaTrasee.add(Route(20, null, TransportType.BUS))
//		listaTrasee.add(Route(22, null, TransportType.BUS))
//		listaTrasee.add(Route(23, null, TransportType.BUS))
//		listaTrasee.add(Route(24, null, TransportType.BUS))
//		listaTrasee.add(Route(25, null, TransportType.BUS))
//		listaTrasee.add(Route(26, null, TransportType.BUS))
//		listaTrasee.add(Route(27, null, TransportType.BUS))
//		listaTrasee.add(Route(28, null, TransportType.BUS))
//		listaTrasee.add(Route(29, null, TransportType.BUS))
//		listaTrasee.add(Route(30, null, TransportType.BUS))
//		listaTrasee.add(Route(31, null, TransportType.BUS))
//		listaTrasee.add(Route(34, null, TransportType.BUS))
//		listaTrasee.add(Route(35, null, TransportType.BUS))
//		listaTrasee.add(Route(36, null, TransportType.BUS))
//		listaTrasee.add(Route(105, null, TransportType.BUS))
		return listaTrasee
	}

	fun mockTransactionsHistory(): List<Transaction> {
		val transactions: MutableList<Transaction> = ArrayList()
		try {
			transactions.add(Transaction(1L, formatterDate.parse("12-JAN-2018"), TransportType.BUS, 2, BigDecimal(- 2.00)))
			transactions.add(Transaction(2L, formatterDate.parse("13-JAN-2018"), TransportType.TBUS, 2, BigDecimal(- 2.00)))
			transactions.add(Transaction(3L, formatterDate.parse("16-JAN-2018"), TransportType.TRAM, 2, BigDecimal(- 2.00)))
			transactions.add(Transaction(4L, formatterDate.parse("22-JAN-2018"), TransportType.BUS, 2, BigDecimal(- 2.00)))
			transactions.add(Transaction(5L, formatterDate.parse("23-JAN-2018"), TransportType.PARKING, 2, BigDecimal(- 1.00)))
			transactions.add(Transaction(6L, formatterDate.parse("24-JAN-2018"), TransportType.NOTAVAILABLE, 2, BigDecimal(+ 15.00)))
			transactions.add(Transaction(7L, formatterDate.parse("30-JAN-2018"), TransportType.TBUS, 2, BigDecimal(- 2.00)))
			transactions.add(Transaction(8L, formatterDate.parse("02-FEB-2018"), TransportType.BUS, 2, BigDecimal(- 2.00)))
			transactions.add(Transaction(9L, formatterDate.parse("10-FEB-2018"), TransportType.PARKING, 2, BigDecimal(- 1.00)))
		} catch (e: ParseException) {
			e.printStackTrace()
		}
		return transactions
	}
}