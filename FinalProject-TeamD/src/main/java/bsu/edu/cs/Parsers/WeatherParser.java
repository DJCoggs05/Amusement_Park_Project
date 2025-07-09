package bsu.edu.cs.Parsers;

import net.minidev.json.JSONArray;

import java.util.LinkedHashMap;

public class WeatherParser extends Parser<Weather> {

    public WeatherParser(ApiInputStream inputStream){
        super(inputStream);
    }
    @Override
    protected String getQuery() {
        return "$..*";
    }


    @Override
    @SuppressWarnings("unchecked")
    protected Weather convertData(JSONArray list) {
        Weather output;

        LinkedHashMap<String, ?> weatherData = null;
        if (list.get(3) instanceof LinkedHashMap<?, ?>) {
            weatherData = (LinkedHashMap<String, ?>) list.get(3);
        }

        LinkedHashMap<String, ?> mainData = null;
        if (list.get(1) instanceof JSONArray temp) {
            mainData = (LinkedHashMap<String, ?>) temp.get(0);
        }

        LinkedHashMap<String, ?> windData = null;
        if (list.get(5) instanceof LinkedHashMap<?, ?>) {
            windData = (LinkedHashMap<String, ?>) list.get(5);
        }

        assert windData != null;
        Number windSpeedNumber = (Number) windData.get("speed");
        double windSpeed = windSpeedNumber.doubleValue();

        assert weatherData != null;
        Number tempNumber = (Number) weatherData.get("temp");
        double temp = tempNumber.doubleValue();

        Number feelsLikeNumber = (Number) weatherData.get("feels_like");
        double feelsLike = feelsLikeNumber.doubleValue();

        Number humidityNumber = (Number) weatherData.get("humidity");
        int humidity = humidityNumber.intValue();


        assert mainData != null;
        output = new Weather(
                temp,
                feelsLike,
                windSpeed,
                humidity,
                (String) mainData.get("icon")
        );


        return output;
    }
}