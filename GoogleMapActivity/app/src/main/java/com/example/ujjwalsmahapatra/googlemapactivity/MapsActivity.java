package com.example.ujjwalsmahapatra.googlemapactivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager manager;
    private LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            Toast.makeText(MapsActivity.this, "Your Location is", Toast.LENGTH_SHORT).show();
            Toast.makeText(MapsActivity.this, "Latitude= " + latitude, Toast.LENGTH_SHORT).show();
            Toast.makeText(MapsActivity.this, "Longitude= " + longitude, Toast.LENGTH_SHORT).show();

            //get street address

            Geocoder coder = new Geocoder (MapsActivity.this);
            try {
                List<Address> addresses=coder.getFromLocation(latitude,longitude,10);
                Address first=addresses.get(0);
                StringBuilder sb=new StringBuilder();
                sb.append(first.getCountryName());//read country name
                sb.append(first.getAddressLine(0));//read address
                //first.getMaxAddressLineIndex();//read land mark
                sb.append(first.getLocale());//read land mark
                sb.append(first.getPostalCode());//read zip code

                // now our current address is ready - display on map

                LatLng sydney=new LatLng(latitude,longitude);
                mMap.addMarker(new MarkerOptions().position(sydney).title(sb.toString()));
                mMap.animateCamera(CameraUpdateFactory.zoomBy(16));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {
            Toast.makeText(MapsActivity.this, "gps is enabled", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderDisabled(String s) {
            Toast.makeText(MapsActivity.this, "gps is disabled", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //get location manager:

        manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(MapsActivity.this, "Permission Denied...Try Again", Toast.LENGTH_SHORT).show();
            return;
        }
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 100, listener);
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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
