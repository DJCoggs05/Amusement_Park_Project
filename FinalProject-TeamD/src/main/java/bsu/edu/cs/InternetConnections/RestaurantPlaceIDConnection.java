package bsu.edu.cs.InternetConnections;

import bsu.edu.cs.Parsers.Park;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class RestaurantPlaceIDConnection extends InternetConnection<Park>{

    private static final String API_KEY;

    static {
        try {
            API_KEY = loadApiKey("google");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load API Key",e);
        }
    }

    @Override
    protected String createRequestUrl(Park park) {
        String urlEncodedParkName = URLEncoder.encode(park.getName(), StandardCharsets.UTF_8);
        urlEncodedParkName = urlEncodedParkName.replace("+","%20");
        return String.format("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%s,%s&rankby=distance&type=restaurant&keyword=%s&key=%s",
                park.getLatitude(),park.getLongitude(),urlEncodedParkName,API_KEY);
    }
}
