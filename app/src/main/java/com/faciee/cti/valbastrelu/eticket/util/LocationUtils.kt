package com.faciee.cti.valbastrelu.eticket.util

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import com.faciee.cti.valbastrelu.eticket.base.ETicketApp


const val REQUEST_CODE_LOCATION_PERMISSION = 8000

object LocationUtils {

	val locationManager = ETicketApp.currentETicketApp.applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager

	fun checkLocationPermissions(activity: Activity): Location? {
		val locationPermissionGranted = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
				ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
		if (locationPermissionGranted)
			return getLastKnownLocation()
		else {
			ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), REQUEST_CODE_LOCATION_PERMISSION)
		}
		return null
	}

	@SuppressLint("MissingPermission")
	private fun getLastKnownLocation(): Location? {
		val providers: List<String> = locationManager.getProviders(true)
		var bestLocation: Location? = null
		for (provider in providers) {
			val location: Location = locationManager.getLastKnownLocation(provider) ?: continue
			if (bestLocation == null || location.accuracy < bestLocation.accuracy) {
				// Found best last known location: %s", l);
				bestLocation = location
			}
		}
		return bestLocation
	}
}