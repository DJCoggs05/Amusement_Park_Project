package bsu.edu.cs.Parsers;

import net.minidev.json.JSONArray;

public class PlaceIDParser extends Parser<String>{
    public PlaceIDParser(ApiInputStream inputStream) {
        super(inputStream);
    }

    @Override
    protected String getQuery() {
        return "$..place_id";
    }

    @Override
    protected String convertData(JSONArray list) {
        String output = null;

        if(!list.isEmpty()){
            output = (String) list.get(0);
        }

        return output;
    }
}
