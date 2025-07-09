package bsu.edu.cs.Parsers;

public class Park {
    final private int id;
    final private String name;
    final private String latitude;
    final private String longitude;

    public Park(int id, String name, String latitude, String longitude){
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getLongitude(){
        return longitude;
    }

    public String getLatitude(){
        return latitude;
    }

}
