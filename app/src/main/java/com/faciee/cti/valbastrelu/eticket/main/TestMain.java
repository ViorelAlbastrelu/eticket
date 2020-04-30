package com.faciee.cti.valbastrelu.eticket.main;

import com.faciee.cti.valbastrelu.eticket.room.entities.Station;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestMain {

	public static void main(String[] args){
		List<Station> statii = new ArrayList<>();
		final StringBuilder builder = new StringBuilder();
		try {
			Document document = Jsoup.connect("https://transurbgalati.ro/statii/").get();
			Elements rows = document.select("td");
			for (int i = 2; i < rows.size(); i+=2) {
				//TODO change dummy route number
//				statii.add(new Station(rows.get(i).text(),rows.get(i+1).text(), 0));
			}
		}catch (IOException e){
			builder.append("Error: ").append(e.getMessage()).append("\n"); }
//		System.out.print(builder.toString());
		System.out.print(statii);
	}
}
