package bsu.edu.cs.Parsers;

import net.minidev.json.JSONArray;

import java.util.LinkedHashMap;

public class RidePositionParser extends Parser<Coordinates> {

    public RidePositionParser(ApiInputStream inputStream){
        super(inputStream);
    }

    @Override
    protected String getQuery() {
        return "$..results";
    }

    @Override
    protected Coordinates convertData(JSONArray list) {

        Double latitude = null;
        Double longitude = null;
        String photoReferenceID = null;
        Coordinates output = null;

        if(!list.isEmpty()) {

            JSONArray results = (JSONArray) list.get(0);

            if (!results.isEmpty()) {

                if (results.get(0) instanceof LinkedHashMap<?, ?>) {

                    @SuppressWarnings("unchecked")
                    LinkedHashMap<String, ?> location = (LinkedHashMap<String, ?>) results.get(0);

                    if (location.get("geometry") instanceof LinkedHashMap<?, ?>) {
                        @SuppressWarnings("unchecked")
                        LinkedHashMap<String, ?> geometry = (LinkedHashMap<String, ?>) location.get("geometry");

                        if (geometry.get("location") instanceof LinkedHashMap<?, ?>) {
                            @SuppressWarnings("unchecked")
                            LinkedHashMap<String, ?> latitudeAndLongitude = (LinkedHashMap<String, ?>) geometry.get("location");
                            latitude = (Double) latitudeAndLongitude.get("lat");
                            longitude = (Double) latitudeAndLongitude.get("lng");
                        }

                    }

                    if(location.get("photos") != null){

                        JSONArray photos = (JSONArray) location.get("photos");
                        if(photos.get(0) instanceof LinkedHashMap<?, ?> photoHashMap){
                            photoReferenceID = (String) photoHashMap.get("photo_reference");
                        }
                    }
                }

                output = new Coordinates(String.valueOf(latitude), String.valueOf(longitude),photoReferenceID);
            }
        }

        return output;

    }
}
