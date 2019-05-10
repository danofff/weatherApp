package com.example.weatherapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherResponse {
        @SerializedName("coord")
        @Expose
        Coord coord;

        @SerializedName("weather")
        @Expose
        ArrayList <Weather> weather;

        @SerializedName("sys")
        @Expose
        Sys sys;

        @SerializedName("wind")
        @Expose
        Wind wind;

        @SerializedName("clouds")
        @Expose
        Clouds clouds;

        @SerializedName("base")
        @Expose
        String base;

        @SerializedName("dt")
        @Expose
        float dt;

        @SerializedName("id")
        @Expose
        float id;

        @SerializedName("name")
        @Expose
        String name;

        @SerializedName("cod")
        @Expose
        int cod;

        @SerializedName("visibility")
        @Expose
        float visibility;

        @SerializedName("main")
        @Expose
        Main main;

        @SerializedName("message")
        @Expose
        String message;
}
