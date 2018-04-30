package com.faciee.cti.valbastrelu.eticket.ui.bus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.ui.common.SectionsPagerAdapter;
import com.faciee.cti.valbastrelu.eticket.ui.main.Chatbot;

public class BusActivity extends AppCompatActivity {
	private static final String TAG = "BusActivity";
	
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link FragmentPagerAdapter} derivative, which will keep every
	 * loaded fragment in memory. If this becomes too memory intensive, it
	 * may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	private SectionsPagerAdapter mSectionsPagerAdapter;
	
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	private ViewPager mViewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bus);
		
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		
		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.container);
		setupViewPager(mViewPager);
		
		TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
		tabLayout.setupWithViewPager(mViewPager);
		
//		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//		fab.setOnClickListener(event -> fragmentTransaction());
	}
	
	private void setupViewPager(ViewPager viewPager){
		SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		sectionsPagerAdapter.addFragment(new FrgTb01Bilet(), getApplication().getString(R.string.tab_name_bilete));    //BILETE
		sectionsPagerAdapter.addFragment(new FrgTb02TraseuMain(), getApplication().getString(R.string.tab_name_trasee));   //TRASEE
		sectionsPagerAdapter.addFragment(new FrgTb02TraseuStatii(), getApplication().getString(R.string.tab_name_istoric)); //ISTORIC //TODO schimbat inapoi la Frg03Istoric
		viewPager.setAdapter(sectionsPagerAdapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_bus, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		//noinspection SimplifiableIfStatement
		switch (id){
			case R.id.action_settings:
				Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show();
				break;
			case R.id.action_chatbot:
				Intent i = new Intent(this, Chatbot.class);
				startActivity(i);
		}
		if (id == R.id.action_settings) {
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	private void fragmentTransaction(){
		FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.container, new FrgTb02TraseuStatii());
		transaction.commit();
	}
}
