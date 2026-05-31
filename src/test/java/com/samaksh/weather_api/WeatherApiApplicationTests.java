package com.samaksh.weather_api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"WEATHER_API_KEY=test-key"
})
class WeatherApiApplicationTests {

	@Test
	void contextLoads() {
	}

}
