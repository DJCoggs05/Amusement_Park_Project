package bsu.edu.cs.Parsers;

public class Coordinates {

    private final String latitude;
    private final String longitude;
    private final String photoReference;

    public Coordinates(String latitude,String longitude){
        this.latitude = latitude;
        this.longitude = longitude;
        this.photoReference = null;
    }

    public Coordinates(String latitude,String longitude,String photoReference){
        this.latitude = latitude;
        this.longitude = longitude;
        this.photoReference = photoReference;
    }

    public String getLatitude(){
        return latitude;
    }

    public String getLongitude(){
        return longitude;
    }

    public String getPhotoReference() {
        return photoReference;
    }
}
