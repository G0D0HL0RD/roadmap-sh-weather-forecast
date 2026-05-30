package com.samaksh.weather_api.service;

import com.samaksh.weather_api.client.WeatherApiClient;
import com.samaksh.weather_api.model.VisualCrossingsResponse;
import com.samaksh.weather_api.model.WeatherResponse;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    private final WeatherApiClient weatherApiClient;

    public WeatherService(WeatherApiClient weatherApiClient) {
        this.weatherApiClient = weatherApiClient;
    }

    public WeatherResponse getWeather(String city) {
        VisualCrossingsResponse apiResponse = weatherApiClient.getWeather(city);

        return new WeatherResponse(apiResponse.getResolvedAddress(), apiResponse.getCurrentConditions().getTemp(),
                apiResponse.getCurrentConditions().getConditions(), apiResponse.getCurrentConditions().getHumidity(),
                apiResponse.getCurrentConditions().getFeelslike(), apiResponse.getCurrentConditions().getPrecip(),
                apiResponse.getDescription());
    }
}
