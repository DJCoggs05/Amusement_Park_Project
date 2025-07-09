package bsu.edu.cs.InternetConnections;

import bsu.edu.cs.Parsers.Coordinates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeatherConnectionTest {

    @Test
    public void createURLRequestTest() {
        WeatherConnection weatherConnectionInstance = new WeatherConnection();

        assertTrue(weatherConnectionInstance.createRequestUrl(new Coordinates("28.417663", "-81.581212")).matches(
                "https://api\\.openweathermap\\.org/data/2\\.5/weather\\?lat=28\\.417663&lon=-81\\.581212&appid=.+"));
    }

}
