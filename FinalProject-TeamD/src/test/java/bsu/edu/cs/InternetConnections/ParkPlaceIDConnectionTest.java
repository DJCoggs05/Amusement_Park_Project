package bsu.edu.cs.InternetConnections;

import bsu.edu.cs.Parsers.Park;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParkPlaceIDConnectionTest {

    @Test
    public void createURLRequestTest(){
        Park testPark = new Park(26,"Disney Magic Kingdom","28.4177","-81.5812");
        ParkPlaceIDConnection placeIDConnection = new ParkPlaceIDConnection();
        assertTrue(placeIDConnection.createRequestUrl(testPark).matches("https://maps\\.googleapis\\.com/maps/api/place/nearbysearch/json\\?location=28\\.4177,-81\\.5812&rankby=distance&type=amusement_park&keyword=Disney%20Magic%20Kingdom&key=[^&]+"));
    }

}
