package com.faciee.cti.valbastrelu.eticket.main;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import com.faciee.cti.valbastrelu.eticket.R;
import com.faciee.cti.valbastrelu.eticket.databinding.ActivityEticketMainBinding;
import com.faciee.cti.valbastrelu.eticket.databinding.NavHeaderEticketMainBinding;
import com.faciee.cti.valbastrelu.eticket.ui.bus.BusMainFragment;
import com.faciee.cti.valbastrelu.eticket.ui.chat.ChatbotActivity;
import com.faciee.cti.valbastrelu.eticket.ui.login.LoginActivity;
import com.faciee.cti.valbastrelu.eticket.ui.parking.FragmentParkingMain;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;


public class ETicketMain extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener {

	FirebaseAuth firebaseAuth;
	private ActivityEticketMainBinding mainBinding;

//	DrawerLayout drawer;
//	NavigationView navigationView;
//	TextView profileName, profileEmail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mainBinding = ActivityEticketMainBinding.inflate(getLayoutInflater());
		setContentView(mainBinding.getRoot());
		mainBinding.drawerLayout.setOnClickListener(this::onFabClick);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		setUserAndEmailToDrawerProfile();

		firebaseAuth = FirebaseAuth.getInstance();
		if (firebaseAuth.getCurrentUser() == null) {
			startActivity(new Intent(this, LoginActivity.class));
			finish();
		}
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, mainBinding.drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		mainBinding.drawerLayout.addDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = (NavigationView) findViewById(R.id.navView);
		navigationView.setNavigationItemSelectedListener(this);
	}

	void onFabClick(View view){
		Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
				.setAction("Action", null).show();
	}

	@Override
	public void onBackPressed() {
		if (mainBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
			mainBinding.drawerLayout.closeDrawer(GravityCompat.START);
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

	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		int id = item.getItemId();
		Intent intent = null;
		if (id == R.id.nav_bus) {
			intent = new Intent(this, BusMainFragment.class);
		} else if (id == R.id.nav_tbus) {
			ETicketApp.toastMessageShort("Trolleybus not implemented yet!");
		} else if (id == R.id.nav_tram) {
			ETicketApp.toastMessageShort("Tramway not implemented yet!");
		} else if (id == R.id.nav_car) {
			intent = new Intent(this, FragmentParkingMain.class);
		} else if (id == R.id.nav_chat) {
			intent = new Intent(this, ChatbotActivity.class);
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

		mainBinding.drawerLayout.closeDrawer(GravityCompat.START);
		if(intent != null) startActivity(intent);
		return true;
	}

	private void setUserAndEmailToDrawerProfile() {
		IETkAppPreferences preferences = ETicketApp.getCurrentETicketApp().getAppPreferences();
		String email = preferences.getCurrentEmail();

		NavHeaderEticketMainBinding navHeader = NavHeaderEticketMainBinding.bind(mainBinding.navView.getHeaderView(0));

		navHeader.profileName.setText("Viorel");
		navHeader.profileEmail.setText(email);
	}

}
