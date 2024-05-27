package com.dicoding.picodiploma.loginwithanimation.view.fragment

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.picodiploma.loginwithanimation.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions

class MapsFragment : Fragment() {

    private lateinit var mMap: GoogleMap

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        mMap = googleMap

        // Add markers
        val marker1 = LatLng(-6.200000, 106.816666) // Jakarta
        val marker2 = LatLng(-7.250445, 112.768845) // Surabaya

        mMap.addMarker(MarkerOptions().position(marker1).title("Marker in Jakarta"))
        mMap.addMarker(MarkerOptions().position(marker2).title("Marker in Surabaya"))

        // Move the camera to the first marker
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker1, 5f))

        // Add polyline between markers
        val polylineOptions = PolylineOptions()
            .add(marker1)
            .add(marker2)
            .width(5f)
            .color(R.color.black)

        mMap.addPolyline(polylineOptions)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}