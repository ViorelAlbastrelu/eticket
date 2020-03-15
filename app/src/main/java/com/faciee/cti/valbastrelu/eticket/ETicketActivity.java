package com.faciee.cti.valbastrelu.eticket;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.faciee.cti.valbastrelu.eticket.ui.bus.BusActivity;
import com.faciee.cti.valbastrelu.eticket.ui.login.LoginActivity;
import com.faciee.cti.valbastrelu.eticket.ui.chat.Chatbot;
import com.faciee.cti.valbastrelu.eticket.util.ETkLog;
import com.google.firebase.auth.FirebaseAuth;


public class ETicketActivity extends AppCompatActivity {
	private static final String TAG = "ETicketActivity";
	
	Toolbar mToolbar;
	private FirebaseAuth mAuth;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eticket);
		mToolbar = findViewById(R.id.toolbar);
		setSupportActionBar(mToolbar);
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
				intent = new Intent(this, Chatbot.class);
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
