package com.faciee.cti.valbastrelu.eticket.ui.settings

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.faciee.cti.valbastrelu.eticket.R
import com.faciee.cti.valbastrelu.eticket.base.ETicketApp
import com.faciee.cti.valbastrelu.eticket.main.ETicketMain
import com.faciee.cti.valbastrelu.eticket.ui.login.LoginActivity

class SettingsFragment : PreferenceFragmentCompat() {
	override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
		setPreferencesFromResource(R.xml.root_preferences, rootKey)
		activity?.let { activity ->
			(activity as ETicketMain).apply {
				val signoutPreference = findPreference<Preference>(this.getString(R.string.settings_pref_key_signout))
				signoutPreference?.setOnPreferenceClickListener {
					var alertDialog: AlertDialog? = null
					alertDialog = AlertDialog.Builder(this)
							.setMessage(R.string.signout_message)
							.setPositiveButton(R.string.afirmativ) { dialog: DialogInterface?, which: Int -> this.signout() }
							.setNegativeButton(R.string.negativ) { dialog: DialogInterface?, which: Int -> alertDialog?.dismiss() }.show()
					true
				}
			}
		}
	}
}