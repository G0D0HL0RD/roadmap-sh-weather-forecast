package com.samaksh.weather_api.cache;

import java.time.Duration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.samaksh.weather_api.model.WeatherResponse;

@Service
public class RedisCacheService {
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisCacheService(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    public WeatherResponse get(String city){
        return(WeatherResponse) redisTemplate.opsForValue().get("weather:" + city.toLowerCase());
    }

    public void put(String city, WeatherResponse response){
        redisTemplate.opsForValue().set("weather:" + city.toLowerCase(), response, Duration.ofHours(12));
    }
}