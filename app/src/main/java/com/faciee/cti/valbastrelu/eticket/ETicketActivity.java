package com.faciee.cti.valbastrelu.eticket;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.faciee.cti.valbastrelu.eticket.databinding.ActivityEticketBinding;
import com.faciee.cti.valbastrelu.eticket.ui.bus.BusActivity;
import com.faciee.cti.valbastrelu.eticket.ui.chat.ChatbotActivity;
import com.faciee.cti.valbastrelu.eticket.ui.login.LoginActivity;
import com.faciee.cti.valbastrelu.eticket.util.ETkLog;
import com.google.firebase.auth.FirebaseAuth;


public class ETicketActivity extends AppCompatActivity {
	private static final String TAG = "ETicketActivity";
	
	private FirebaseAuth mAuth;
	private ActivityEticketBinding activityEticketBinding;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activityEticketBinding = ActivityEticketBinding.inflate(getLayoutInflater());
		setContentView(activityEticketBinding.getRoot());
		setSupportActionBar(activityEticketBinding.includeToolbar.toolbar);
		findViewById(R.id.fab).setOnClickListener(this::clickOnFab);
		mAuth = FirebaseAuth.getInstance();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.options_menu, menu);
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
				intent = new Intent(this, BusActivity.class);
				break;
			case R.id.action_chatbot:
				intent = new Intent(this, ChatbotActivity.class);
				break;
			case R.id.action_sign_out:
				mAuth.signOut();
				intent = new Intent(this, LoginActivity.class);
				finish();
				break;
		}
		startActivity(intent);
		return super.onOptionsItemSelected(item);
	}
	
	public void clickOnFab(View view){
		ETkLog.f(TAG, "Logging in to a file from FAB", this);
		Toast.makeText(this, "No implementation", Toast.LENGTH_SHORT).show();
		startActivity(new Intent(this, BusActivity.class));
		finish();
	}
}
