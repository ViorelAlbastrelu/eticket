package com.faciee.cti.valbastrelu.eticket.ui.bus;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.ui.bus.model.BusActivityModel;
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.SectionsPagerAdapter;
import com.faciee.cti.valbastrelu.eticket.ui.bus.model.Bilet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BusActivity extends AppCompatActivity {
	
	private static final String TAG = "BusActivity";
	BusActivityModel busActivityModel;
	
	@BindView(R.id.container) ViewPager mViewPager;
	@BindView(R.id.tabs) TabLayout mTabLayout;
	
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
		SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		sectionsPagerAdapter.addFragment(new FrgTb01Bilet(), getApplication().getString(R.string.tab_name_bilete));    //BILETE
		sectionsPagerAdapter.addFragment(new FrgTb02TraseuMain(), getApplication().getString(R.string.tab_name_trasee));   //TRASEE
		sectionsPagerAdapter.addFragment(new FrgTb02TraseuStatii(), getApplication().getString(R.string.tab_name_istoric)); //ISTORIC //TODO schimbat inapoi la Frg03Istoric
		viewPager.setAdapter(sectionsPagerAdapter);
	}
	
	@OnClick(R.id.fab)
	void addBilet(View view){
		busActivityModel.insertBilet(new Bilet(100, true, 2,2));
	}
}
