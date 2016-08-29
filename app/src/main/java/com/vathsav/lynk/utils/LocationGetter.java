package com.vathsav.lynk.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

/**
 * Created by vathsav on 29/08/16.
 */
public class LocationGetter {
    LocationManager locationManager;

    public LocationGetter(LocationManager locationManager, final Activity activity) {
        this.locationManager = locationManager;
        locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Toast.makeText(
                        activity.getApplicationContext(),
                        "Lat: " + location.getLatitude() + " Long: "
                                + location.getLongitude(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if (ActivityCompat
                .checkSelfPermission(activity.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity.getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
        }
    }
}
