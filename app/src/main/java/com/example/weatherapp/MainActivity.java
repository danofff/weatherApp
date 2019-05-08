package com.example.weatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<WeatherResponse> {

    private static final String appId = "09c94c5dafeaf8e3555c7ef1c084b8c7";
    private static final String units = "metric";
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Button getCurrentWeather;

    private String lat;
    private String lon;
    Map<String, String> options = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getCurrentWeather = findViewById(R.id.buttonGetWeather);
        implemetLocationService();

    }

    @Override
    public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
        WeatherResponse obj = response.body();
        ((TextView) findViewById(R.id.temperature)).setText("temperature " + obj.main.temp);
        ((TextView) findViewById(R.id.cityName)).setText(obj.name);
    }

    @Override
    public void onFailure(Call<WeatherResponse> call, Throwable t) {

    }

    public void implemetLocationService() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                lat = Double.toString(location.getLatitude());
                lon = Double.toString(location.getLongitude());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                }, 10);
                return;
            }
        }
        else{
            getLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    getLocation();
                break;
        }
    }

    private void getLocation(){
        getCurrentWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationManager.requestLocationUpdates("gps", 500, 0, locationListener);
                sendRequest();
            }
        });
    }

    private void sendRequest(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherApi api = retrofit.create(WeatherApi.class);
        options.put("lat", lat);
        options.put("lon", lon);
        options.put("appid", appId);
        options.put("units", units);

        api.getData(options).enqueue(this);
    }
}
