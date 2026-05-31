package com.samaksh.weather_api.model;

import java.io.Serializable;

public class WeatherResponse implements Serializable{
    private String city;
    private double temperature;
    private String condition;
    private double humidity;
    private double feelsLike;
    private Double precip;
    private String description;

    public WeatherResponse(String city, double temperature, String condition, double humidity, double feelsLike, Double precip, String description) {
        this.city = city;
        this.temperature = temperature;
        this.condition = condition;
        this.humidity = humidity;
        this.feelsLike = feelsLike;
        this.precip = precip;
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getCondition() {
        return condition;
    }

    public Double getPrecipitation() {
        return precip;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getFeelsLike() {
        return feelsLike;
    }
    
    public String getDescription() {
        return description;
    }
}
