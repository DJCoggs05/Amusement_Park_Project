package bsu.edu.cs.Parsers;

import net.minidev.json.JSONArray;

import java.util.*;

public class RideParser extends Parser<List<Ride>> {

    public RideParser(ApiInputStream inputStream) {
        super(inputStream);
    }

    @Override
    protected String getQuery() {
        return "$..rides[*]";
    }

    @Override
    protected List<Ride> convertData(JSONArray list) {
        List<Ride> ridesList = new ArrayList<>();

        for (Object rideItem : list) {
            if (rideItem instanceof LinkedHashMap<?, ?>) {
                @SuppressWarnings("unchecked")
                LinkedHashMap<String, ?> rideConverted = (LinkedHashMap<String, ?>) rideItem;
                ridesList.add(new Ride(
                        (int) rideConverted.get("id"),
                        (String) rideConverted.get("name"),
                        (Boolean) rideConverted.get("is_open"),
                        ((Number) rideConverted.get("wait_time")).intValue()
                ));
            }
        }
        ridesList.sort(Comparator.comparing(Ride::getName));
        return ridesList;
    }
}