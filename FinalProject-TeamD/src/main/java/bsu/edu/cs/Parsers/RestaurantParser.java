package bsu.edu.cs.Parsers;

import net.minidev.json.JSONArray;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

public class RestaurantParser extends Parser<List<Restaurant>>{

    public RestaurantParser(ApiInputStream inputStream){
        super(inputStream);
    }

    @Override
    protected String getQuery() {
        return "$.results";
    }

    @Override
    protected List<Restaurant> convertData(JSONArray list) {
        List<Restaurant> restaurantsList = new ArrayList<>();
        Coordinates coordinates = null;
        Double priceLevel = null;
        Double rating = null;
        String photoReferenceID = null;

        for(Object restaurant: list){

            if(restaurant instanceof LinkedHashMap<?, ?> restaurantHashMap){

                if(restaurantHashMap.get("geometry") instanceof LinkedHashMap<?, ?> geometryHashMap) {

                    if(geometryHashMap.get("location") instanceof LinkedHashMap<?, ?> locationHashMap) {
                        coordinates = new Coordinates(Double.toString((Double) locationHashMap.get("lat")),Double.toString((Double) locationHashMap.get("lng")));
                    }
                }


                if(restaurantHashMap.get("photos") != null){

                    JSONArray photos = (JSONArray) restaurantHashMap.get("photos");
                    if(photos.get(0) instanceof LinkedHashMap<?, ?> photoHashMap){
                        photoReferenceID = (String) photoHashMap.get("photo_reference");
                    }
                }

                if(restaurantHashMap.get("price_level") != null){
                    priceLevel = ((Number) restaurantHashMap.get("price_level")).doubleValue();
                }

                if(restaurantHashMap.get("rating") != null){
                    rating = ((Number) restaurantHashMap.get("rating")).doubleValue();
                }

                restaurantsList.add(new Restaurant((String) restaurantHashMap.get("name"),coordinates,rating,
                        priceLevel,(String) restaurantHashMap.get("place_id"),photoReferenceID));
            }
        }
        restaurantsList.sort(Comparator.comparing(Restaurant::getName));
        return restaurantsList;
    }
}
