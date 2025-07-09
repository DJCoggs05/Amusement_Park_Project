package bsu.edu.cs.Parsers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParkTest {

    @Test
    public void getParkIdTest(){
        Park testPark = new Park(26,"Disney Magic Kingdom","28.4177","-81.5812");
        assertEquals(26,testPark.getId());
    }

    @Test
    public void getParkNameTest(){
        Park testPark = new Park(26,"Disney Magic Kingdom","28.4177","-81.5812");
        assertEquals("Disney Magic Kingdom",testPark.getName());
    }

    @Test
    public void getParkLatitudeTest(){
        Park testPark = new Park(26,"Disney Magic Kingdom","28.4177","-81.5812");
        assertEquals("28.4177",testPark.getLatitude());
    }

    @Test
    public void getParkLongitudeTest(){
        Park testPark = new Park(26,"Disney Magic Kingdom","28.4177","-81.5812");
        assertEquals("-81.5812",testPark.getLongitude());
    }


}
