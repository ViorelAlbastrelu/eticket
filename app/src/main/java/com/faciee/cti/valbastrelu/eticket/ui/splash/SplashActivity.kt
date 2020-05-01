package com.faciee.cti.valbastrelu.eticket.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.faciee.cti.valbastrelu.eticket.base.BaseActivity
import com.faciee.cti.valbastrelu.eticket.databinding.ActivitySplashBinding
import com.faciee.cti.valbastrelu.eticket.main.ETicketMain
import com.faciee.cti.valbastrelu.eticket.repo.SplashRepository

class SplashActivity : BaseActivity() {

	lateinit var splashBinding: ActivitySplashBinding
	lateinit var splashVM: SplashVM

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		splashBinding = ActivitySplashBinding.inflate(layoutInflater)
		setContentView(splashBinding.root)

		splashVM = ViewModelProvider(this, SplashVM.getFactory(eTicketApp, SplashRepository(eTicketApp.database))).get(SplashVM::class.java)

		splashVM.isLoading.observe(this, Observer { isLoading ->
			if (! isLoading) {
				startActivity(Intent(this, ETicketMain::class.java))
				finish()
			}
		})
	}
}
