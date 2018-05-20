package com.faciee.cti.valbastrelu.eticket;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.faciee.cti.valbastrelu.eticket.ui.bus.BusActivity;
import com.faciee.cti.valbastrelu.eticket.ui.bus.FrgTb02TraseuStatii;
import com.faciee.cti.valbastrelu.eticket.ui.login.LoginActivity;
import com.faciee.cti.valbastrelu.eticket.ui.main.Chatbot;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ETicketActivity extends AppCompatActivity {
	
	@BindView(R.id.toolbar) Toolbar mToolbar;
	private FirebaseAuth mAuth;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eticket);
		setSupportActionBar(mToolbar);
		ButterKnife.bind(this);
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
				break;
		}
		startActivity(intent);
		return super.onOptionsItemSelected(item);
	}
	
	@OnClick(R.id.fab)
	public void clickOnFab(View view){
		startActivity(new Intent(this, BusActivity.class));
		Toast.makeText(this, "No implementation", Toast.LENGTH_SHORT).show();
//		fragmentTransaction();
	}
	
	private void fragmentTransaction(){
		FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.container, new FrgTb02TraseuStatii());
		transaction.addToBackStack("statii");
		transaction.commit();
	}
}
