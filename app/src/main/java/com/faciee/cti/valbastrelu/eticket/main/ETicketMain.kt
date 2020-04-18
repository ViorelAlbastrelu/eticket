package com.faciee.cti.valbastrelu.eticket.main

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.view.GravityCompat
import com.faciee.cti.valbastrelu.eticket.R
import com.faciee.cti.valbastrelu.eticket.base.BaseActivity
import com.faciee.cti.valbastrelu.eticket.base.ETicketApp
import com.faciee.cti.valbastrelu.eticket.extensions.toastMessageShort
import com.faciee.cti.valbastrelu.eticket.databinding.ActivityEticketMainBinding
import com.faciee.cti.valbastrelu.eticket.databinding.NavHeaderEticketMainBinding
import com.faciee.cti.valbastrelu.eticket.ui.bus.BusMainFragment
import com.faciee.cti.valbastrelu.eticket.ui.chat.ChatbotActivity
import com.faciee.cti.valbastrelu.eticket.ui.login.LoginActivity
import com.faciee.cti.valbastrelu.eticket.ui.parking.FragmentParkingMain
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class ETicketMain : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

	private lateinit var mainBinding: ActivityEticketMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		mainBinding = ActivityEticketMainBinding.inflate(layoutInflater)
		setContentView(mainBinding.root)

		mainBinding.drawerLayout.setOnClickListener { view: View? -> onFabClick(view) }
		setActionBar(mainBinding.mainContent.toolbar)
		setUserAndEmailToDrawerProfile()
		if (eTicketApp.firebaseAuth.currentUser == null) {
			startActivity(LoginActivity.prepareIntent(this))
			finish()
		}
//		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//		this, mainBinding.drawerLayout, mainBinding.mainContent.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//		mainBinding.drawerLayout.addDrawerListener(toggle);
//		toggle.syncState();
		mainBinding.navView.setNavigationItemSelectedListener(this)
	}

	private fun onFabClick(view: View?) {
		Snackbar.make(view !!, "Replace with your own action", Snackbar.LENGTH_LONG)
				.setAction("Action", null).show()
	}

	override fun onBackPressed() {
		if (mainBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
			mainBinding.drawerLayout.closeDrawer(GravityCompat.START)
		} else {
			super.onBackPressed()
		}
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean { // Inflate the menu; this adds items to the action bar if it is present.
		menuInflater.inflate(R.menu.eticket_main, menu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean { // Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
		val id = item.itemId
		return if (id == R.id.action_settings) {
			true
		} else super.onOptionsItemSelected(item)
	}

	override fun onNavigationItemSelected(item: MenuItem): Boolean {
		val id = item.itemId
		var intent: Intent? = null
		if (id == R.id.nav_bus) {
			intent = Intent(this, BusMainFragment::class.java)
		} else if (id == R.id.nav_tbus) {
			eTicketApp.toastMessageShort("Trolleybus not implemented yet!")
		} else if (id == R.id.nav_tram) {
			eTicketApp.toastMessageShort("Tramway not implemented yet!")
		} else if (id == R.id.nav_car) {
			intent = Intent(this, FragmentParkingMain::class.java)
		} else if (id == R.id.nav_chat) {
			intent = Intent(this, ChatbotActivity::class.java)
		} else if (id == R.id.nav_signout) {
			AlertDialog.Builder(this)
					.setMessage(R.string.signout_message)
					.setPositiveButton(R.string.afirmativ) { dialog: DialogInterface?, which: Int ->
						eTicketApp.firebaseAuth.signOut()
						startActivity(Intent(this, LoginActivity::class.java))
					}
					.setNegativeButton(R.string.negativ) { dialog: DialogInterface?, which: Int -> }.show()
		}
		mainBinding.drawerLayout.closeDrawer(GravityCompat.START)
		intent?.let { startActivity(it) }
		return true
	}

	private fun setUserAndEmailToDrawerProfile() {
		val email = ETicketApp.currentETicketApp.appPreferences.getCurrentEmail()
		val navHeader = NavHeaderEticketMainBinding.bind(mainBinding.navView.getHeaderView(0))
		navHeader.profileName.text = "Viorel"
		navHeader.profileEmail.text = email
	}

	companion object{
		fun prepareIntent(activity: Activity) = Intent(activity, ETicketMain::class.java)
	}
}