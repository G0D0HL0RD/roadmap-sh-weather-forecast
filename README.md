# roadmap-sh-weather-forecast
The Weather API project having RESTful backend service built to fetch, process, and serve real-time weather data by integrating with a third-party weather intelligence platform (such as OpenWeatherMap or WeatherAPI).
https://roadmap.sh/projects/weather-api-wrapper-service

## Features

- Fetch current weather data from Visual Crossing Weather API
- Redis caching with 12-hour TTL
- Global exception handling
- API rate limiting
- Environment variable configuration
- Dockerized deployment
- Clean layered architecture

---

## Tech Stack

- Java 21
- Spring Boot 4
- Maven
- Redis
- Docker
- Visual Crossing Weather API
- Bucket4j

---

## Architecture

```text
Client
  |
  v
WeatherController
  |
  v
WeatherService
  |
  +-------------------+
  |                   |
  v                   |
Redis Cache           |
  |                   |
Cache Hit?            |
  |                   |
Yes --> Return        |
                      |
No                    |
  |                   |
  v                   |
WeatherApiClient      |
  |                   |
  v                   |
Visual Crossing API   |
  |                   |
  v                   |
Store in Redis -------+
  |
  v
Return Response
```

---

## API Endpoint

### Get Weather

```http
GET /weather?city={cityName}
```

### Example

```http
GET /weather?city=mumbai
```

### Success Response

```json
{
  "city": "Mumbai",
  "temperature": 31.3,
  "condition": "Partially cloudy",
  "humidity": 73.2,
  "feelsLike": 39.5,
  "precipitation": 0.0,
  "description": "Similar temperatures continuing with a chance of rain Thursday, Friday & Saturday."
}
```

### City Not Found

```json
{
  "message": "City not found: invalidCity"
}
```

Status Code:

```http
404 Not Found
```

### Weather Provider Error

```json
{
  "message": "Weather service temporarily unavailable"
}
```

Status Code:

```http
503 Service Unavailable
```

---

## Redis Caching

Weather responses are cached using Redis.

### Cache Key Format

```text
weather:{city}
```

Example:

```text
weather:mumbai
```

### Cache TTL

```text
12 Hours
```

Benefits:

- Reduces external API calls
- Faster response times
- Reduces API usage costs

---

## Rate Limiting

The API uses Bucket4j to prevent abuse.

Current configuration:

```text
100 requests per minute
```

Exceeded requests receive:

```http
429 Too Many Requests
```

---

## Environment Variables

The application uses environment variables for configuration.

| Variable | Description |
|-----------|-------------|
| WEATHER_API_KEY | Visual Crossing API Key |
| SPRING_DATA_REDIS_HOST | Redis Host |

Example:

```properties
weather.api.key=${WEATHER_API_KEY}
spring.data.redis.host=${SPRING_DATA_REDIS_HOST:localhost}
```

---

## Running Locally

### Clone Repository

```bash
git clone https://github.com/G0D0HL0RD/roadmap-sh-weather-forecast.git
cd roadmap-sh-weather-forecast
```

### Start Redis

```bash
docker run -d --name redis -p 6379:6379 redis
```

### Set Environment Variable

PowerShell:

```powershell
$env:WEATHER_API_KEY="YOUR_API_KEY"
```

### Run Application

```bash
mvn spring-boot:run
```

---

## Docker

### Build

```bash
mvn clean package -DskipTests

docker build -t weather-api .
```

### Run

```bash
docker run -p 8080:8080 \
-e WEATHER_API_KEY=YOUR_API_KEY \
-e SPRING_DATA_REDIS_HOST=host.docker.internal \
weather-api
```

---

## Project Structure

```text
src
├── main
│   ├── java
│   │   └── com.samaksh.weather_api
│   │       ├── cache
│   │       ├── client
│   │       ├── config
│   │       ├── controller
│   │       ├── exception
│   │       ├── filter
│   │       ├── model
│   │       └── service
│   └── resources
│       └── application.properties
└── test
```