package com.example.peta1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.location.Location
import android.widget.TextView
import android.widget.Toast
import com.example.peta1.databinding.ActivityMapsBinding

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    lateinit var mMap: GoogleMap
    lateinit var binding: ActivityMapsBinding
    lateinit var lat1 : EditText
    lateinit var long1 : EditText
    lateinit var btn1 : Button
    lateinit var lat2 : EditText
    lateinit var long2 : EditText
    lateinit var btn2 : Button
    lateinit var tvResult : TextView
    private var mark1 = LatLng(-52.0,150.0)
    private var mark2 = LatLng(-52.0,150.0)
    private var result = FloatArray(1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        btn1 = findViewById(R.id.btnMark1)
        btn2 = findViewById(R.id.btnMark2)
        lat1 = (findViewById(R.id.lat1))
        long1 = findViewById(R.id.long1)
        lat2 = findViewById(R.id.lat2)
        long2 = findViewById(R.id.long2)
        tvResult = findViewById(R.id.tvJarak)
        btn1.setOnClickListener {
            if(lat1.text.isEmpty() || long1.text.isEmpty()){
                Toast.makeText(this, "Lat or long is empty", Toast.LENGTH_SHORT).show()
            }
            else{
                mark1 = LatLng(lat1.text.toString().toDouble(), long1.text.toString().toDouble())
                mapFragment.getMapAsync(this)
                if (lat1.text.isNotEmpty() && lat2.text.isNotEmpty()&&long1.text.isNotEmpty() && long2.text.isNotEmpty()){
                    Location.distanceBetween(lat1.text.toString().toDouble(),long1.text.toString().toDouble(),
                        lat2.text.toString().toDouble(),long2.text.toString().toDouble(), result)
                    tvResult.text = result[0].toString() + "M"
                }
            }
        }
        btn2.setOnClickListener {
            if(lat2.text.isEmpty() || long2.text.isEmpty()){
                Toast.makeText(this, "Lat or long is empty", Toast.LENGTH_SHORT).show()
            }
            else{
                mark2 = LatLng(lat2.text.toString().toDouble(), long2.text.toString().toDouble())
                mapFragment.getMapAsync(this)
                if (lat1.text.isNotEmpty() && lat2.text.isNotEmpty()&&long1.text.isNotEmpty() && long2.text.isNotEmpty()){
                    Location.distanceBetween(lat1.text.toString().toDouble(),long1.text.toString().toDouble(),
                        lat2.text.toString().toDouble(),long2.text.toString().toDouble(), result)
                    tvResult.text = result[0].toString() + "M"
                }
            }
        }
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.clear()
        mMap.addMarker(MarkerOptions().position(mark1).title("Marker 1"))
        mMap.addMarker(MarkerOptions().position(mark2).title("Marker 2"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mark1))

    }
}