package com.example.weatherapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;

import static android.content.Context.LOCATION_SERVICE;

public class MyLocation {
//    private LocationManager locationManager;
//    private LocationListener locationListener;
//    private Context context;
//
//    public MyLocation(Context context) {
//        this.context = context;
//        locationManager = (LocationManager) this.context.getSystemService(LOCATION_SERVICE);
//        locationListener = new LocationListener() {
//            @Override
//            public void onLocationChanged(Location location) {
//
//            }
//
//            @Override
//            public void onStatusChanged(String provider, int status, Bundle extras) {
//
//            }
//
//            @Override
//            public void onProviderEnabled(String provider) {
//
//            }
//
//            @Override
//            public void onProviderDisabled(String provider) {
//
//            }
//        };
//    }
//
//    public String getLatitude() {
//        return null;
//    }
//
//    public String getLontitude() {
//        return null;
//    }
//
//    public void procesisng() {
//        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
//            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(new Activity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.ACCESS_COARSE_LOCATION,
//                Manifest.permission.INTERNET}, 10);
//                return;
//            }
//        }
//        else{
//
//        }
//        locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);
//    }

}
