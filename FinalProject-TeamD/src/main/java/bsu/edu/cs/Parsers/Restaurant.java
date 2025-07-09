package bsu.edu.cs.Parsers;

public class Restaurant {

    private final String name;
    private final Coordinates coordinates;
    private final Double rating;
    private final Double price_level;
    private final String placeID;
    private final String photoReferenceID;


    public Restaurant(String name, Coordinates coordinates, Double rating, Double priceLevel, String placeID, String photoReferenceID) {
        this.name = name;
        this.coordinates = coordinates;
        this.rating = rating;
        this.price_level = priceLevel;
        this.placeID = placeID;
        this.photoReferenceID = photoReferenceID;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Double getRating() {
        return rating;
    }

    public Double getPriceLevel() {
        return price_level;
    }

    public String getPlaceID() {
        return placeID;
    }

    public String getPhotoReferenceID() {
        return photoReferenceID;
    }
}
