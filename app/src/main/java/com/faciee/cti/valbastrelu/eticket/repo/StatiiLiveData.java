package com.faciee.cti.valbastrelu.eticket.repo;

import android.annotation.SuppressLint;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.faciee.cti.valbastrelu.eticket.room.dao.StatieDao;
import com.faciee.cti.valbastrelu.eticket.room.entities.Statie;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class StatiiLiveData extends LiveData<List<Statie>> {
	private static final String TAG = "StatiiLiveData";
	private static String URL_STATII = "https://transurb-galati.com/%s/";
	private List<String> numeStatii = new ArrayList<>();
	MutableLiveData<List<String>> numeStatiiLiveData = new MutableLiveData<>();
	
	
	public StatiiLiveData(int numarTraseu) {
		loadStatii(numarTraseu);
	}
	
	@SuppressLint("StaticFieldLeak")
	private void loadStatii(int numarTraseu) {
		new AsyncTask<Void,Void,List<Statie>>() {
			@Override
			protected List<Statie> doInBackground(Void... params) {
				String url = (numarTraseu == 0 ?
				String.format(URL_STATII, "statii"):
				String.format(URL_STATII, "statii-traseu-"+numarTraseu));
				List<Statie> statieList = new ArrayList<>();
				try {
					Document document = Jsoup.connect(url).get();
					Elements rows = document.select("td");
					for (int i = 2; i < rows.size(); i += 2) {
						Statie statie = new Statie(numarTraseu, rows.get(i).text(), rows.get(i + 1).text());
						statieList.add(statie);
						numeStatii.add(statie.getNumeStatie());
//						statieDao.insertStatii(statie);
					}
				} catch (IOException e) {
					Log.e(TAG, "loadStatii: " + e.getMessage(), null);
				}
				return statieList;
			}
			@Override
			protected void onPostExecute(List<Statie> data) {
				setValue(data);
				numeStatiiLiveData.setValue(numeStatii);
			}
		}.execute();
	}

	public LiveData<List<String>> getNumeStatii() {
		return numeStatiiLiveData;
	}
}
