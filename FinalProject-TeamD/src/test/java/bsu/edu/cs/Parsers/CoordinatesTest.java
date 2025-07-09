package bsu.edu.cs.Parsers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoordinatesTest {

    @Test
    public void coordinatesGetLatitudeTest(){
        Coordinates coordinates = new Coordinates("25.789","-81.7568");
        assertEquals("25.789",coordinates.getLatitude());
    }

    @Test
    public void coordinatesGetLongitudeTest(){
        Coordinates coordinates = new Coordinates("25.789","-81.7568");
        assertEquals("-81.7568",coordinates.getLongitude());
    }

    @Test
    public void coordinatesGetPhotoIDTest(){
        Coordinates coordinates = new Coordinates("25.789","-81.7568","asdfjlaksldfjkasdkfljk");
        assertEquals("asdfjlaksldfjkasdkfljk",coordinates.getPhotoReference());
    }


}
