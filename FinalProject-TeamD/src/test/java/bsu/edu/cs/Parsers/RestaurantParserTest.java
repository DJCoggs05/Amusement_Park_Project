package bsu.edu.cs.Parsers;

import bsu.edu.cs.Exceptions.noItemFoundException;
import bsu.edu.cs.Exceptions.openInputStreamException;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestaurantParserTest {

    @Test
    public void getQueryTest() throws openInputStreamException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("restaurantSample.json");
        assert sampleFile != null;
        RestaurantParser restaurantParser = new RestaurantParser(new ApiInputStream(sampleFile));
        assertEquals("$.results",restaurantParser.getQuery());
    }

    @Test
    public void convertDataTest() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("restaurantSample.json");
        assert sampleFile != null;
        RestaurantParser restaurantParser = new RestaurantParser(new ApiInputStream(sampleFile));
        JSONArray parsedData = restaurantParser.extractData(new ByteArrayInputStream(restaurantParser.inputStreamInstance.inputStream));
        List<Restaurant> convertedObject = restaurantParser.convertData(parsedData);
        assertEquals(20,convertedObject.size());
    }

    @Test
    public void extractDataTest() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("restaurantSample.json");
        assert sampleFile != null;
        RestaurantParser restaurantParser = new RestaurantParser(new ApiInputStream(sampleFile));
        JSONArray parsedData = restaurantParser.extractData(new ByteArrayInputStream(restaurantParser.inputStreamInstance.inputStream));
        assertNotNull(parsedData);
    }

    @Test
    public void extractDataTestNoItems() throws openInputStreamException, IOException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("restaurantSample.json");
        assert sampleFile != null;
        RestaurantParser restaurantParser = new RestaurantParser(new ApiInputStream(sampleFile));

        //Closed to test file exception
        sampleFile.close();

        try {
            restaurantParser.extractData(sampleFile);
            fail("Expected noItemFoundException");
        }catch (noItemFoundException e){
            assertEquals("Could not find the item you were looking for!",e.getMessage());
        }
    }

    @Test
    public void parseTest() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("restaurantSample.json");
        assert sampleFile != null;
        RestaurantParser restaurantParser = new RestaurantParser(new ApiInputStream(sampleFile));
        assertEquals(20,restaurantParser.parse().size());
    }

}
