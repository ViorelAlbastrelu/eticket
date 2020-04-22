package com.faciee.cti.valbastrelu.eticket.repo;

import android.annotation.SuppressLint;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.faciee.cti.valbastrelu.eticket.room.entities.Station;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class StatiiLiveData extends LiveData<List<Station>> {
	private static final String TAG = "StatiiLiveData";
	private static String URL_STATII = "https://transurb-galati.com/%s/";
	private List<String> numeStatii = new ArrayList<>();
	MutableLiveData<List<String>> numeStatiiLiveData = new MutableLiveData<>();
	
	
	public StatiiLiveData(int numarTraseu) {
		loadStatii(numarTraseu);
	}
	
	@SuppressLint("StaticFieldLeak")
	private void loadStatii(int numarTraseu) {
		new AsyncTask<Void,Void,List<Station>>() {
			@Override
			protected List<Station> doInBackground(Void... params) {
				String url = (numarTraseu == 0 ?
				String.format(URL_STATII, "statii"):
				String.format(URL_STATII, "statii-traseu-"+numarTraseu));
				List<Station> stationList = new ArrayList<>();
				try {
					Document document = Jsoup.connect(url).get();
					Elements rows = document.select("td");
					for (int i = 2; i < rows.size(); i += 2) {
						//TODO routeNumber is dummy set to 0
						Station station = new Station(numarTraseu, rows.get(i).text(), rows.get(i + 1).text(), 0);
						stationList.add(station);
						numeStatii.add(station.getName());
//						statieDao.insertStatii(statie);
					}
				} catch (IOException e) {
					Log.e(TAG, "loadStatii: " + e.getMessage(), null);
				}
				return stationList;
			}
			@Override
			protected void onPostExecute(List<Station> data) {
				setValue(data);
				numeStatiiLiveData.setValue(numeStatii);
			}
		}.execute();
	}

	public LiveData<List<String>> getNumeStatii() {
		return numeStatiiLiveData;
	}
}
