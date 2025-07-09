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

public class PlaceIDParserTest {
    @Test
    public void getQueryTest() throws openInputStreamException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("placeIDSample.json");
        assert sampleFile != null;
        PlaceIDParser placeIDParserInstance = new PlaceIDParser(new ApiInputStream(sampleFile));
        assertEquals("$..place_id",placeIDParserInstance.getQuery());
    }

    @Test
    public void convertDataTest() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("placeIDSample.json");
        assert sampleFile != null;
        PlaceIDParser placeIDParserInstance = new PlaceIDParser(new ApiInputStream(sampleFile));
        JSONArray parsedData = placeIDParserInstance.extractData(new ByteArrayInputStream(placeIDParserInstance.inputStreamInstance.inputStream));
        String convertedData = placeIDParserInstance.convertData(parsedData);
        assertInstanceOf(String.class,convertedData);
    }

    @Test
    public void extractDataTest() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("placeIDSample.json");
        assert sampleFile != null;
        PlaceIDParser placeIDParserInstance = new PlaceIDParser(new ApiInputStream(sampleFile));
        JSONArray parsedData = placeIDParserInstance.extractData(new ByteArrayInputStream(placeIDParserInstance.inputStreamInstance.inputStream));
        assertNotNull(parsedData);
    }

    @Test
    public void extractDataTestNoItems() throws openInputStreamException, IOException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("placeIDSample.json");
        assert sampleFile != null;
        PlaceIDParser placeIDParserInstance = new PlaceIDParser(new ApiInputStream(sampleFile));

        //Closed to test file exception
        sampleFile.close();

        try {
            placeIDParserInstance.extractData(sampleFile);
            fail("Expected noItemFoundException");
        }catch (noItemFoundException e){
            assertEquals("Could not find the item you were looking for!",e.getMessage());
        }
    }

    @Test
    public void parseTest() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("placeIDSample.json");
        assert sampleFile != null;
        PlaceIDParser placeIDParserInstance = new PlaceIDParser(new ApiInputStream(sampleFile));
        assertEquals("ChIJgUulalN-3YgRGoTaWM2LawY",placeIDParserInstance.parse());
    }

    @Test
    public void ridePositionParseTest() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("ridePositionSample.json");
        assert sampleFile != null;
        PlaceIDParser placeIDParserInstance = new PlaceIDParser(new ApiInputStream(sampleFile));
        assertEquals("ChIJw4ci_I9-3YgRlc53KntyI9k",placeIDParserInstance.parse());
    }
}
