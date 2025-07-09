package bsu.edu.cs.Parsers;

import bsu.edu.cs.Exceptions.noItemFoundException;
import bsu.edu.cs.Exceptions.openInputStreamException;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestaurantWebsiteParserTest {

    @Test
    public void getQueryTest() throws openInputStreamException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("restaurantWebsiteSample.json");
        assert sampleFile != null;
        RestaurantWebsiteParser restaurantWebsiteParser = new RestaurantWebsiteParser(new ApiInputStream(sampleFile));
        assertEquals("$.*",restaurantWebsiteParser.getQuery());
    }

    @Test
    public void convertDataTest() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("restaurantWebsiteSample.json");
        assert sampleFile != null;
        RestaurantWebsiteParser restaurantWebsiteParser = new RestaurantWebsiteParser(new ApiInputStream(sampleFile));
        JSONArray parsedData = restaurantWebsiteParser.extractData(new ByteArrayInputStream(restaurantWebsiteParser.inputStreamInstance.inputStream));
        String convertedObject = restaurantWebsiteParser.convertData(parsedData);
        assertEquals("https://www.rainforestcafe.com/location/rainforest-cafe-disney-world-animal-kingdom/",convertedObject);
    }

    @Test
    public void extractDataTest() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("restaurantWebsiteSample.json");
        assert sampleFile != null;
        RestaurantWebsiteParser restaurantWebsiteParser = new RestaurantWebsiteParser(new ApiInputStream(sampleFile));
        JSONArray parsedData = restaurantWebsiteParser.extractData(new ByteArrayInputStream(restaurantWebsiteParser.inputStreamInstance.inputStream));
        assertNotNull(parsedData);
    }

    @Test
    public void extractDataTestNoItems() throws openInputStreamException, IOException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("restaurantWebsiteSample.json");
        assert sampleFile != null;
        RestaurantWebsiteParser restaurantWebsiteParser = new RestaurantWebsiteParser(new ApiInputStream(sampleFile));

        //Closed to test file exception
        sampleFile.close();

        try {
            restaurantWebsiteParser.extractData(sampleFile);
            fail("Expected noItemFoundException");
        }catch (noItemFoundException e){
            assertEquals("Could not find the item you were looking for!",e.getMessage());
        }
    }

    @Test
    public void parseTest() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("restaurantWebsiteSample.json");
        assert sampleFile != null;
        RestaurantWebsiteParser restaurantWebsiteParser = new RestaurantWebsiteParser(new ApiInputStream(sampleFile));
        assertEquals("https://www.rainforestcafe.com/location/rainforest-cafe-disney-world-animal-kingdom/",restaurantWebsiteParser.parse());
    }

}
