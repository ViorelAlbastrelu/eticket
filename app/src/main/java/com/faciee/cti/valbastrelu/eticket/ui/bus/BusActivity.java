package com.faciee.cti.valbastrelu.eticket.ui.bus;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.ui.bus.model.BusActivityModel;
import com.faciee.cti.valbastrelu.eticket.ui.bus.model.Traseu;
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.SectionsPagerAdapter;
import com.faciee.cti.valbastrelu.eticket.ui.bus.model.Bilet;
import com.faciee.cti.valbastrelu.eticket.ui.common.i.TransportViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BusActivity extends AppCompatActivity implements TransportViewActivity{
	
	private static final String TAG = "BusActivity";
	BusActivityModel busActivityModel;
	
	@BindView(R.id.container) ViewPager mViewPager;
	@BindView(R.id.tabs) TabLayout mTabLayout;
	SectionsPagerAdapter sectionsPagerAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bus);
		ButterKnife.bind(this);
		busActivityModel = ViewModelProviders.of(this).get(BusActivityModel.class);
		setupViewPager(mViewPager);
		mTabLayout.setupWithViewPager(mViewPager);
	}
	
	private void setupViewPager(ViewPager viewPager) {
		sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		sectionsPagerAdapter.addFragment(new FrgTb01Bilet(), getApplication().getString(R.string.tab_name_ticket));    //BILETE
		sectionsPagerAdapter.addFragment(new FrgTb02Trasee(), getApplication().getString(R.string.tab_name_routes));   //TRASEE
		sectionsPagerAdapter.addFragment(new FrgTb03Istoric(), getApplication().getString(R.string.tab_name_history)); //ISTORIC
		viewPager.setAdapter(sectionsPagerAdapter);
	}
	
	public void showStatiiForTraseu(Traseu traseu) {
		FrgTb02Statii frgTb02Statii = FrgTb02Statii.statiiPentruTraseu(traseu.getNrTraseu());
		Log.d(TAG, "showStatiiForTraseu: Called!");
		getSupportFragmentManager()
				.beginTransaction()
				.addToBackStack("product")
				.replace(R.id.container,
						frgTb02Statii, null).commit();
	}
	
	@OnClick(R.id.fab)
	void addBilet(View view){
		busActivityModel.insertBilet(new Bilet(100, true, 2,2));
	}
	
	@Override
	public void setActivityTitle(String activityTitle) {
	
	}
	
	@Override
	public void setActivityTitle(int activityResId) {
	
	}
	
	@Override
	public void setToolbarText(String toolbarText) {
	
	}
	
	@Override
	public void setToolbarText(int toolbarResId) {
	
	}
}
