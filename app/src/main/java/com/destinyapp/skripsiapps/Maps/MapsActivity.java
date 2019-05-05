package com.destinyapp.skripsiapps.Maps;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.destinyapp.skripsiapps.DashboardActivity;
import com.destinyapp.skripsiapps.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Intent data = getIntent();
        //Get IntentData
        final String Nama = data.getStringExtra("Nama");
        final String Remarks = data.getStringExtra("Remarks");
        final String Photo = data.getStringExtra("Photo");
        final String Detail = data.getStringExtra("Detail");
        final String Lahir = data.getStringExtra("Lahir");
        final String Wafat = data.getStringExtra("Wafat");
        final String Langitude = data.getStringExtra("Lang");
        final String Longitude = data.getStringExtra("Long");
        kembali = (Button)findViewById(R.id.btnKembali);
        //

        // Add a marker in Sydney and move the camera
        LatLng location = new LatLng(Double.parseDouble(Langitude), Double.parseDouble(Longitude));

        mMap.addMarker(new MarkerOptions().position(location).title("Makam "+Nama));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,10F));

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goInput = new Intent(MapsActivity.this, DashboardActivity.class);
                goInput.putExtra("DetailExtra","detail");
                goInput.putExtra("Nama",Nama);
                goInput.putExtra("Remarks",Remarks);
                goInput.putExtra("Photo",Photo);
                goInput.putExtra("Detail",Detail);
                goInput.putExtra("Lahir",Lahir);
                goInput.putExtra("Wafat",Wafat);
                goInput.putExtra("Lang",Langitude);
                goInput.putExtra("Long",Longitude);
                MapsActivity.this.startActivities(new Intent[]{goInput});
            }
        });
    }
}
