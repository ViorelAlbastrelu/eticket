package com.faciee.cti.valbastrelu.eticket.base

import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

abstract class MapBaseFragment<VM : ViewModel> : BaseFragment<VM>(), OnMapReadyCallback {

	private lateinit var mMap: GoogleMap
	protected lateinit var mapFragment: SupportMapFragment

	abstract val mapId: Int

//	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//		val view = super.onCreateView(inflater, container, savedInstanceState)
//		mapFragment = childFragmentManager.findFragmentById(mapId) as SupportMapFragment
//		mapFragment.getMapAsync(this)
//		return view
//	}

	override fun onMapReady(googleMap: GoogleMap) {
		mMap = googleMap
		// Add a marker in Sydney and move the camera
		val sydney = LatLng(- 34.0, 151.0)
		mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
		mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
	}
}