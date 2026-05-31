package com.samaksh.weather_api.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.samaksh.weather_api.exception.CityNotFoundException;
import com.samaksh.weather_api.exception.WeatherProviderException;
import com.samaksh.weather_api.model.VisualCrossingsResponse;

@Component
public class WeatherApiClient {

    @Value("${weather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public VisualCrossingsResponse getWeather(String city) {
        String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/" + city
                + "?unitGroup=metric&key=" + apiKey + "&contentType=json";
        try {
            return restTemplate.getForObject(url, VisualCrossingsResponse.class);
        } catch (HttpClientErrorException.BadRequest e) {
            throw new CityNotFoundException("City not found: " + city);
        } catch (Exception e) {
            throw new WeatherProviderException("Weather service temporarily unavailable");
        }
    }
}
