package com.samaksh.weather_api.model;

public class WeatherResponse {
    private String city;
    private int temperature;
    private String condition;


    public WeatherResponse(String city, int temperature, String condition){
        this.city = city;
        this.temperature = temperature;
        this.condition = condition;
    }

    public String getCity(){
        return city;
    }

    public int getTemperature(){
        return temperature;
    }

    public String getCondition(){
        return condition;
    }
}
