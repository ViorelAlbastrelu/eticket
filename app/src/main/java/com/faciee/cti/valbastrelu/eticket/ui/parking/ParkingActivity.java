package com.faciee.cti.valbastrelu.eticket.ui.parking;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.SectionsPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ParkingActivity extends AppCompatActivity{
	
	@BindView(R.id.container) ViewPager mViewPager;
	@BindView(R.id.tabs) TabLayout mTabLayout;
	
	private SectionsPagerAdapter mSectionsPagerAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parking);
		ButterKnife.bind(this);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		setupViewPager(mViewPager);
		mTabLayout.setupWithViewPager(mViewPager);
	}
	
	@OnClick(R.id.fab)
	void onClickFab(View view){
		//TODO implement add ticket with ViewModel or remove?
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_parking, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		
		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	public static class PlaceholderFragment extends Fragment {
		private static final String ARG_SECTION_NUMBER = "section_number";
		
		public PlaceholderFragment() {
		}
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
		                         Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_parking, container, false);
			TextView textView = (TextView) rootView.findViewById(R.id.section_label);
			textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
			return rootView;
		}
	}
	
	private void setupViewPager(ViewPager viewPager) {
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		mSectionsPagerAdapter.addFragment(new FrgTb01Bilet(), getApplication().getString(R.string.tab_name_ticket));    //BILETE
		mSectionsPagerAdapter.addFragment(new FrgTb02Parcari(), getApplication().getString(R.string.tab_name_parking));   //PARCARI
		mSectionsPagerAdapter.addFragment(new FrgTb03Istoric(), getApplication().getString(R.string.tab_name_history)); //ISTORIC
		viewPager.setAdapter(mSectionsPagerAdapter);
	}
}
