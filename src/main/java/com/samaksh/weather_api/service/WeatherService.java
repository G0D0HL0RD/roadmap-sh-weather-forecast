package com.samaksh.weather_api.service;

import com.samaksh.weather_api.model.WeatherResponse;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    public WeatherResponse getWeather(String city){
        return new WeatherResponse(city, 25, "Sunny");
    }
}
