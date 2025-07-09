package bsu.edu.cs.InternetConnections;

import bsu.edu.cs.Parsers.Coordinates;

import java.io.IOException;

public class WeatherConnection extends InternetConnection<Coordinates> {

    private static final String API_KEY;

    static {
        try {
            API_KEY = loadApiKey("weather");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load API Key",e);
        }
    }


    @Override
    protected String createRequestUrl(Coordinates latitudeAndLongitude) {
        return "https://api.openweathermap.org/data/2.5/weather?lat=" + latitudeAndLongitude.getLatitude() + "&lon=" + latitudeAndLongitude.getLongitude() + "&appid=" + API_KEY + "&units=imperial";
    }

}