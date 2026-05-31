package com.samaksh.weather_api.service;

import com.samaksh.weather_api.cache.RedisCacheService;
import com.samaksh.weather_api.client.WeatherApiClient;
import com.samaksh.weather_api.model.VisualCrossingsResponse;
import com.samaksh.weather_api.model.WeatherResponse;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    private final WeatherApiClient weatherApiClient;
    private final RedisCacheService redisCacheService;

    public WeatherService(WeatherApiClient weatherApiClient, RedisCacheService redisCacheService) {
        this.weatherApiClient = weatherApiClient;
        this.redisCacheService = redisCacheService;
    }

    public WeatherResponse getWeather(String city) {
        WeatherResponse cached = redisCacheService.get(city);

        if (cached != null) {
            System.out.println("CACHE HIT");

            return cached;
        }
        System.out.println("CACHE MISS");
        VisualCrossingsResponse apiResponse = weatherApiClient.getWeather(city);
        WeatherResponse response = new WeatherResponse(apiResponse.getResolvedAddress(),
                apiResponse.getCurrentConditions().getTemp(),
                apiResponse.getCurrentConditions().getConditions(), apiResponse.getCurrentConditions().getHumidity(),
                apiResponse.getCurrentConditions().getFeelslike(), apiResponse.getCurrentConditions().getPrecip(),
                apiResponse.getDescription());

        redisCacheService.put(city, response);

        return response;
    }
}
