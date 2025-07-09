package bsu.edu.cs.InternetConnections;

import bsu.edu.cs.Parsers.Park;
import bsu.edu.cs.Parsers.RideSearch;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class RidePositionConnection extends InternetConnection<RideSearch>{

    private static final String API_KEY;

    static {
        try {
            API_KEY = loadApiKey("google");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load API Key",e);
        }
    }
    @Override
    protected String createRequestUrl(RideSearch searchItem) {
        Park park = searchItem.getPark();
        String urlEncodedRideName = URLEncoder.encode(searchItem.getRideName(), StandardCharsets.UTF_8);
        urlEncodedRideName = urlEncodedRideName.replace("+","%20");
        return String.format("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%s,%s&rankby=distance&keyword=%s&key=%s",
                park.getLatitude(),park.getLongitude(), urlEncodedRideName,API_KEY);
    }

}

