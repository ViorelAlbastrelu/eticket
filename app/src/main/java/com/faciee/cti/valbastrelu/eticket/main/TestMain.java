package com.faciee.cti.valbastrelu.eticket.main;

import com.faciee.cti.valbastrelu.eticket.util.model.Statie;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestMain {
	
	
	public static void main(String[] args){
		List<Statie> statii = new ArrayList<>();
		final StringBuilder builder = new StringBuilder();
		try {
			Document document = Jsoup.connect("https://transurb-galati.com/statii/").get();
			Elements rows = document.select("td");
			for (int i = 2; i < rows.size(); i+=2) {
				statii.add(new Statie(rows.get(i).text(),rows.get(i+1).text()));
			}
		}catch (IOException e){
			builder.append("Error: ").append(e.getMessage()).append("\n"); }
//		System.out.print(builder.toString());
		System.out.print(statii);
	}
}
