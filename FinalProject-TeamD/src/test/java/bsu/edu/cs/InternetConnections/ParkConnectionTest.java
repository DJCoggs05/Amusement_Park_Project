package bsu.edu.cs.InternetConnections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkConnectionTest{

    @Test
    public void createURLRequestTest(){
        ParkConnection parkConnectionInstance = new ParkConnection();
        assertEquals("https://queue-times.com/parks.json",parkConnectionInstance.createRequestUrl("test"));
    }
}
