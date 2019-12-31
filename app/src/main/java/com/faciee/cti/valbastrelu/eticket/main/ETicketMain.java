package com.faciee.cti.valbastrelu.eticket.main;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.ui.bus.BusActivity;
import com.faciee.cti.valbastrelu.eticket.ui.chat.Chatbot;
import com.faciee.cti.valbastrelu.eticket.ui.login.LoginActivity;
import com.faciee.cti.valbastrelu.eticket.ui.parking.ParkingActivity;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ETicketMain extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener {
	
	FirebaseAuth firebaseAuth;
	@BindView(R.id.drawer_layout) DrawerLayout drawer;
	@BindView(R.id.nav_view) NavigationView navigationView;
	TextView profileName, profileEmail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eticket_main);
		ButterKnife.bind(this);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		setUserAndEmailToDrawerProfile();
		
		firebaseAuth = FirebaseAuth.getInstance();
		if (firebaseAuth.getCurrentUser() == null) {
			startActivity(new Intent(this, LoginActivity.class));
			finish();
		}
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();
		
		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
	}
	
	@OnClick(R.id.fab)
	void onFabClick(View view){
		Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
				.setAction("Action", null).show();
	}
	
	@Override
	public void onBackPressed() {
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.eticket_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		int id = item.getItemId();
		Intent intent = null;
		if (id == R.id.nav_bus) {
			intent = new Intent(this, BusActivity.class);
		} else if (id == R.id.nav_tbus) {
			ETicketApp.toastMessageShort("Trolleybus not implemented yet!");
		} else if (id == R.id.nav_tram) {
			ETicketApp.toastMessageShort("Tramway not implemented yet!");
		} else if (id == R.id.nav_car) {
			intent = new Intent(this, ParkingActivity.class);
		} else if (id == R.id.nav_chat) {
			intent = new Intent(this, Chatbot.class);
		} else if (id == R.id.nav_signout) {
			new AlertDialog.Builder(this)
					.setMessage(R.string.signout_message)
					.setPositiveButton(R.string.afirmativ, (dialog, which) -> {
						firebaseAuth.signOut();
						startActivity(new Intent(this, LoginActivity.class));
						finish();
					})
					.setNegativeButton(R.string.negativ, (dialog, which) -> {
//						ETicketApp.toastMessageShort("Canceled");
					}).show();
		}
		
		drawer.closeDrawer(GravityCompat.START);
		if(intent != null) startActivity(intent);
		return true;
	}
	
	private void setUserAndEmailToDrawerProfile() {
		IETkAppPreferences preferences = ETicketApp.getCurrentETicketApp().getAppPreferences();
		String email = preferences.getCurrentEmail();
		
		View header = navigationView.getHeaderView(0);
		profileName = header.findViewById(R.id.profileName);
		profileEmail = header.findViewById(R.id.profileEmail);
		
		profileName.setText("Viorel");
		profileEmail.setText(email);
	}

}
