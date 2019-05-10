package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherByCityName extends AppCompatActivity implements Callback<WeatherResponse> {

    private static final String appId = "09c94c5dafeaf8e3555c7ef1c084b8c7";
    private static final String units = "metric";
    Map<String, String> options = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_by_city_name);
    }

    public void getCityWeather(View view) {
        hideKeyboard();
        String cityName = ((EditText) findViewById(R.id.cityName)).getText().toString();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherApi api = retrofit.create(WeatherApi.class);
        options.put("appid", appId);
        options.put("units", units);
        options.put("q", cityName);

        api.getData(options).enqueue(this);
    }

    @Override
    public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
        WeatherResponse obj = response.body();
        if(obj!=null){
            switch (obj.cod){
                case 200:
                    showData(obj);
                    break;
                case 404:
                    Toast toast1 = Toast.makeText(this, obj.message,Toast.LENGTH_LONG);
                    toast1.show();
                    break;
                default:
                    Toast toast2 = Toast.makeText(this, "something went wrong!!!",Toast.LENGTH_LONG);
                    toast2.show();
            }
        }
        else{
            Toast toast = Toast.makeText(this, "something went wrong!!!",Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    public void onFailure(Call<WeatherResponse> call, Throwable t) {
        Toast toast = Toast.makeText(this, "something went wrong!!!",Toast.LENGTH_LONG);
        toast.show();
    }

    public void showData(WeatherResponse obj){
        StringBuilder info = new StringBuilder();
        info.append((int)obj.main.temp).append("\u00B0"+"C ").append(obj.weather.get(0).description).append(" humidity ").append(obj.main.humidity);
        ((TextView) findViewById(R.id.temperature)).setText(info);
        ((TextView) findViewById(R.id.cityNameText)).setText(obj.name);
        ImageView iconImageView = findViewById(R.id.weatherIcon);
        Picasso.get().load("http://openweathermap.org/img/w/"+obj.weather.get(0).icon+".png").into(iconImageView);
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
