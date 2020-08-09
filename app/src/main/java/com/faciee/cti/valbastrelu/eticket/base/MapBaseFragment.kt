package com.faciee.cti.valbastrelu.eticket.base

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import com.faciee.cti.valbastrelu.eticket.util.LocationUtils
import com.faciee.cti.valbastrelu.eticket.util.REQUEST_CODE_LOCATION_PERMISSION
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng

abstract class MapBaseFragment<VM : ViewModel> : BaseFragment<VM>(), OnMapReadyCallback {

	protected lateinit var mMap: GoogleMap
	protected lateinit var mapFragment: SupportMapFragment

	abstract val mapId: Int

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		mapFragment = childFragmentManager.findFragmentById(mapId) as SupportMapFragment
		mapFragment.onCreate(savedInstanceState)
		mapFragment.getMapAsync(this)
	}

	override fun onMapReady(googleMap: GoogleMap) {
		mMap = googleMap
		LocationUtils.checkLocationPermissions(requireActivity())?.let { lastKnowLocation ->
			setMapToLastKnownLocation(lastKnowLocation)
		}
		// Add a marker in Sydney and move the camera
//		val sydney = LatLng(- 34.0, 151.0)
//		mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//		mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
	}

	override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)
		if (requestCode == REQUEST_CODE_LOCATION_PERMISSION) {
			if (grantResults.isNotEmpty() && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
				LocationUtils.checkLocationPermissions(requireActivity())?.let {
					setMapToLastKnownLocation(it)
				}
			}
		}
	}

	private fun setMapToLastKnownLocation(location: Location) {
		mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude, location.longitude), 10f))
	}

	override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)
		mapFragment.onSaveInstanceState(outState)
	}

	override fun onResume() {
		super.onResume()
		mapFragment.onResume()
	}

	override fun onPause() {
		super.onPause()
		mapFragment.onPause()
	}

	override fun onDestroy() {
		super.onDestroy()
		mapFragment.onDestroy()
	}

	override fun onLowMemory() {
		super.onLowMemory()
		mapFragment.onLowMemory()
	}


}