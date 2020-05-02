package com.faciee.cti.valbastrelu.eticket.ui.settings

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.faciee.cti.valbastrelu.eticket.R
import com.faciee.cti.valbastrelu.eticket.base.ETicketApp
import com.faciee.cti.valbastrelu.eticket.ui.login.LoginActivity

class SettingsFragment : PreferenceFragmentCompat() {
	override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
		setPreferencesFromResource(R.xml.root_preferences, rootKey)
		val signoutPreference = findPreference<Preference>("signout")
		signoutPreference?.setOnPreferenceClickListener {
			var alertDialog: AlertDialog? = null
			activity?.let {
				alertDialog = AlertDialog.Builder(it)
						.setMessage(R.string.signout_message)
						.setPositiveButton(R.string.afirmativ) { dialog: DialogInterface?, which: Int ->
							ETicketApp.currentETicketApp.firebaseAuth.signOut()
							startActivity(LoginActivity.prepareIntent(it))
							//TODO keep main app in background and add flag in LoginActivity to intent SINGLE_TOP
							it.finish()
						}
						.setNegativeButton(R.string.negativ) { dialog: DialogInterface?, which: Int -> alertDialog?.dismiss() }.show()

			}
			true
		}
	}
}