package com.example.case_fire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class EmergencyMainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String senderPhone, senderToken, senderAddress, senderLatitude, senderLongitude;
    private TextView senderAddressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_main);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        senderPhone = extras.getString("sender-phone");
        senderToken = extras.getString("sender-token");
        senderAddress = extras.getString("sender_address");
        senderLatitude = extras.getString("sender_latitude");
        senderLongitude = extras.getString("sender_longitude");
        Log.e("patient", senderLatitude+","+senderLongitude);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        senderAddressText = findViewById(R.id.textview_sender_address);
        senderAddressText.setText(senderAddress);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        LatLng patient = new LatLng(Double.parseDouble(senderLatitude), Double.parseDouble(senderLongitude));

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(patient);
        markerOptions.title("patient");
        mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(patient));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }

}

