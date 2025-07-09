package bsu.edu.cs.Parsers;

public class Weather {

    final private double temperature;
    final private double feels_like;
    final private double windSpeed;
    final private int humidity;
    final private String iconID;

    public Weather(double temperature, double feelsLike, double windSpeed, int humidity,String iconID){
        this.temperature = temperature;
        this.feels_like = feelsLike;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.iconID = iconID;

    }


    public double getTemperature(){
        return temperature;
    }

    public double getFeels_like() {
        return feels_like;
    }

    public double getWindSpeed(){
        return windSpeed;
    }

    public int getHumidity(){
        return humidity;
    }

    public String getIconID(){
        return iconID;
    }

}