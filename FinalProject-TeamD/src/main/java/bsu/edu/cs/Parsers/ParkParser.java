package bsu.edu.cs.Parsers;

import net.minidev.json.JSONArray;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class ParkParser extends Parser<HashMap<String,Park>> {

    public ParkParser(ApiInputStream inputStream){
        super(inputStream);
    }
    @Override
    protected String getQuery() {
        return "$..parks[*]";
    }

    @Override
    protected HashMap<String,Park> convertData(JSONArray list) {

        HashMap<String,Park> parkMap = new HashMap<>();

        for(Object parkItem: list) {
            if (parkItem instanceof LinkedHashMap<?, ?>) {
                @SuppressWarnings("unchecked")
                LinkedHashMap<String, ?> parkConverted = (LinkedHashMap<String, ?>) parkItem;
                parkMap.put((String) parkConverted.get("name"),new Park((int) parkConverted.get("id"), (String) parkConverted.get("name"),
                        (String) parkConverted.get("latitude"), (String) parkConverted.get("longitude")
                ));
            }
        }
        return parkMap;
    }
}
