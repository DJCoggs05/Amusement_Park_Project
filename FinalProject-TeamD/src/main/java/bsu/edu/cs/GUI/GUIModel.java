package bsu.edu.cs.GUI;

import bsu.edu.cs.Exceptions.networkErrorException;
import bsu.edu.cs.Exceptions.noItemFoundException;
import bsu.edu.cs.Exceptions.openInputStreamException;
import bsu.edu.cs.InternetConnections.ParkConnection;
import bsu.edu.cs.InternetConnections.RestaurantPlaceIDConnection;
import bsu.edu.cs.InternetConnections.RideConnection;
import bsu.edu.cs.InternetConnections.WeatherConnection;
import bsu.edu.cs.Parsers.*;
import com.jayway.jsonpath.JsonPath;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GUIModel {

    public Map<String, Park> fetchParks() throws networkErrorException, openInputStreamException, noItemFoundException {
        ParkConnection parkConnection = new ParkConnection();
        //Search is left blank because we are just grabbing the parks so there's no value to give it but a blank string
        ApiInputStream apiInputStream = new ApiInputStream(parkConnection.search(""));
        ParkParser parkParserInstance = new ParkParser(apiInputStream);
        return parkParserInstance.parse();
    }


    public String getWaitTimeColor(int waitTime){
        String output;

        if(waitTime < 45){
            output = "low-wait-time";
        }else if(waitTime <= 90){
            output = "medium-wait-time";
        }else{
            output = "high-wait-time";
        }
        return output;
    }

    public String convertMinToHours(int minutes){
        String output;
        int hours;
        int extraMinutes;
        if (minutes < 0){
            output = "Invalid: minute cannot be negative";
        }
        else if(minutes < 60){
            output = minutes + " min";
        }else if(minutes % 60 == 0){
            hours = minutes / 60;
            output = String.format("%d hr",hours);
        }else{
            hours = minutes / 60;
            extraMinutes = minutes % 60;
            output = String.format("%d hr %d min",hours,extraMinutes);
        }

        return output;
    }

    public Weather getWeather(String latitude, String longitude) throws networkErrorException, openInputStreamException, noItemFoundException {
        WeatherConnection weatherConnectionInstance = new WeatherConnection();
        WeatherParser weatherParserInstance = new WeatherParser(new ApiInputStream(weatherConnectionInstance.search(new Coordinates(latitude, longitude))));
        return weatherParserInstance.parse();
    }

    public List<Ride> fetchRides(Park park) throws networkErrorException, openInputStreamException, noItemFoundException {
        RideConnection rideConnection = new RideConnection();
        RideParser rideParser = new RideParser(new ApiInputStream(rideConnection.search(park.getId())));
        return rideParser.parse();
    }

    public List<Restaurant> fetchRestaurants(Park park) throws networkErrorException, noItemFoundException, openInputStreamException {
        RestaurantPlaceIDConnection restaurantPlaceIDConnection = new RestaurantPlaceIDConnection();
        RestaurantParser restaurantParser = new RestaurantParser(new ApiInputStream(restaurantPlaceIDConnection.search(park)));
        return restaurantParser.parse();
    }

    public String getPhotoURl(String photoReference) throws IOException {
       String apiKey = JsonPath.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("ApiKeys.json"),"google");
       return String.format("https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=%s&key=%s",photoReference,apiKey);
    }

}
