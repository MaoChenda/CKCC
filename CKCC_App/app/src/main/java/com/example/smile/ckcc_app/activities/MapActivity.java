package com.example.smile.ckcc_app.activities;

import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.smile.ckcc_app.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap ruppMap;
    private MapView mapView;
    private final double ruppLat = 11.569028;
    private final double ruppLng = 104.890610;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mapView = (MapView)findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();

        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();

        mapView.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mapView.onDestroy();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        ruppMap = googleMap;

        // Move camera to RUPP
        LatLng ruppLatLng = new LatLng(ruppLat, ruppLng);
        CameraUpdate ruppCameraUpdate = CameraUpdateFactory.newLatLngZoom(ruppLatLng, 17);
        ruppMap.animateCamera(ruppCameraUpdate);

        // Add marker
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title("Royal University of Phnom Penh");
        markerOptions.snippet("RUPP");
        markerOptions.position(ruppLatLng);
        ruppMap.addMarker(markerOptions);

    }
}
