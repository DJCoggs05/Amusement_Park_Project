package bsu.edu.cs.Parsers;

public class RideSearch {
    private final String rideName;
    private final Park park;

    public RideSearch(String rideName, Park park){
        this.rideName = rideName;
        this.park = park;
    }

    public String getRideName(){
        return rideName;
    }

    public Park getPark(){
        return park;
    }

}
