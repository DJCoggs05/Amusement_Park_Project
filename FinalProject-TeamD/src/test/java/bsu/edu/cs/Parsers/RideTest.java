package bsu.edu.cs.Parsers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RideTest {

    @Test
    public void getRideIdTest(){
        Ride testRide = new Ride(134, "Jungle Cruise",true,45);
        assertEquals(134,testRide.getId());
    }

    @Test
    public void getRideNameTest(){
        Ride testRide = new Ride(134, "Jungle Cruise",true,45);
        assertEquals("Jungle Cruise",testRide.getName());
    }

    @Test
    public void getRideIsOpenTest(){
        Ride testRide = new Ride(134, "Jungle Cruise",true,45);
        assertTrue(testRide.getIsOpen());
    }

    @Test
    public void getRideWaitTimeest(){
        Ride testRide = new Ride(134, "Jungle Cruise",true,45);
        assertEquals(45,testRide.getWaitTime());
    }
}
