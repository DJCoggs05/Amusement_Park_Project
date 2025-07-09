package bsu.edu.cs.InternetConnections;


import java.io.IOException;

public class ReviewConnection extends InternetConnection<String>{

    private static final String API_KEY;

    static {
        try {
            API_KEY = loadApiKey("google");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load API Key",e);
        }
    }
    @Override
    protected String createRequestUrl(String placeID) {
        return String.format("https://maps.googleapis.com/maps/api/place/details/json?place_id=%s&fields=name,rating,reviews&key=%s",
                placeID,API_KEY);
    }

}
