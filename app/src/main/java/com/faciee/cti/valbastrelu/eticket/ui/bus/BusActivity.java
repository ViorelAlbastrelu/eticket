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
import android.view.View;
import android.widget.Toast;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.ui.common.adapters.SectionsPagerAdapter;
import com.faciee.cti.valbastrelu.eticket.ui.login.LoginActivity;
import com.faciee.cti.valbastrelu.eticket.ui.main.Chatbot;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BusActivity extends AppCompatActivity {
	private static final String TAG = "BusActivity";
	private FirebaseAuth mAuth;
	
	@BindView(R.id.toolbar)     Toolbar mToolbar;
	@BindView(R.id.container)   ViewPager mViewPager;
	@BindView(R.id.tabs)        TabLayout mTabLayout;
	
	
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link FragmentPagerAdapter} derivative, which will keep every
	 * loaded fragment in memory. If this becomes too memory intensive, it
	 * may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bus);
		ButterKnife.bind(this);
		setSupportActionBar(mToolbar);
		setupViewPager(mViewPager);
		mTabLayout.setupWithViewPager(mViewPager);
		mAuth = FirebaseAuth.getInstance();
	}
	
	@OnClick(R.id.fab)
	public void clickOnFab(View view){
		fragmentTransaction();
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
		Intent intent = null;
		switch (id){
			case R.id.action_settings:
				Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show();
				break;
			case R.id.action_chatbot:
				intent = new Intent(this, Chatbot.class);
				startActivity(intent);
				break;
			case R.id.action_sign_out:
				mAuth.signOut();
				intent = new Intent(this, LoginActivity.class);
				startActivity(intent);
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void fragmentTransaction(){
		FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.container, new FrgTb02TraseuStatii());
		transaction.addToBackStack("statii");
		transaction.commit();
	}
}
