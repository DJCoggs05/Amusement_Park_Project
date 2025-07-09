package bsu.edu.cs.InternetConnections;

public class ParkConnection extends InternetConnection<String>{

    @Override
    protected String createRequestUrl(String searchItem) {
        //Parameter doesn't need to be used because this simply is fetching one item
        return "https://queue-times.com/parks.json";
    }
}
