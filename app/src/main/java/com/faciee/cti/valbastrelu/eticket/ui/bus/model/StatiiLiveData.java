package com.faciee.cti.valbastrelu.eticket.ui.bus.model;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class StatiiLiveData extends LiveData<List<String>> {
	private static final String TAG = "StatiiLiveData";
	private static String URL_STATII = "https://transurb-galati.com/%s/";
	
	StatiiLiveData(int numarTraseu) {
		loadStatii(numarTraseu);
	}
	
	@SuppressLint("StaticFieldLeak")
	private void loadStatii(int numarTraseu) {
		new AsyncTask<Void,Void,List<String>>() {
			@Override
			protected List<String> doInBackground(Void... params) {
				String url = (numarTraseu == 0 ?
				String.format(URL_STATII, "statii"):
				String.format(URL_STATII, "statii-traseu-"+numarTraseu));
				List<String> statieList = new ArrayList<>();
				try {
					Document document = Jsoup.connect(url).get();
					Elements rows = document.select("td");
					for (int i = 2; i < rows.size(); i += 2) {
						statieList.add(rows.get(i).text());
					}
				} catch (IOException e) {
					Log.e(TAG, "loadStatii: " + e.getMessage(), null);
				}
				return statieList;
			}
			@Override
			protected void onPostExecute(List<String> data) {
				setValue(data);
			}
		}.execute();
	}
}
