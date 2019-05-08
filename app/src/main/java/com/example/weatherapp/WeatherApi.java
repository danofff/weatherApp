package com.example.weatherapp;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;


public interface WeatherApi {
    @GET("data/2.5/weather")
    Call<WeatherResponse>getData(@QueryMap Map<String, String> options);

}
