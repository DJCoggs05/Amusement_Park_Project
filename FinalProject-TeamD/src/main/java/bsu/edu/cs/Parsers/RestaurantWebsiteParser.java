package bsu.edu.cs.Parsers;

import net.minidev.json.JSONArray;

import java.util.LinkedHashMap;

public class RestaurantWebsiteParser extends Parser<String> {

    public RestaurantWebsiteParser(ApiInputStream inputStream){
        super(inputStream);
    }

    @Override
    protected String getQuery() {
        return "$.*";
    }

    @Override
    protected String convertData(JSONArray list) {

        String output = null;

        if(list.get(1) instanceof LinkedHashMap<?,?> websiteConverted){

            if(websiteConverted.get("website") != null){
                output = (String) websiteConverted.get("website");
            }

        }
        return output;
    }
}
