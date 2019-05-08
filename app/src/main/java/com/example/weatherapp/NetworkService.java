package com.example.weatherapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService service;
    private static final String BASE_URL = "https://api.openweathermap.org/";
    private Retrofit retrofit;

    private NetworkService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (service == null) {
            service = new NetworkService();
        }
        return service;
    }
    public WeatherApi getWeatherApi(){
        return retrofit.create(WeatherApi.class);
    }
}
