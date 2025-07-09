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

public class RidePositionParserTest {

    @Test
    public void getQueryTest() throws openInputStreamException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("ridePositionSample.json");
        assert sampleFile != null;
        RidePositionParser ridePositionParserInstance = new RidePositionParser(new ApiInputStream(sampleFile));
        assertEquals("$..results",ridePositionParserInstance.getQuery());
    }

    @Test
    public void convertDataTest() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("ridePositionSample.json");
        assert sampleFile != null;
        RidePositionParser ridePositionParserInstance = new RidePositionParser(new ApiInputStream(sampleFile));
        JSONArray parsedData = ridePositionParserInstance.extractData(new ByteArrayInputStream(ridePositionParserInstance.inputStreamInstance.inputStream));
        Coordinates coordinates = ridePositionParserInstance.convertData(parsedData);
        assertEquals("28.3548553 -81.5927921", coordinates.getLatitude() + " " + coordinates.getLongitude());
    }

    @Test
    public void extractDataTest() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("ridePositionSample.json");
        assert sampleFile != null;
        RidePositionParser ridePositionParserInstance = new RidePositionParser(new ApiInputStream(sampleFile));
        JSONArray parsedRevisions = ridePositionParserInstance.extractData(new ByteArrayInputStream(ridePositionParserInstance.inputStreamInstance.inputStream));
        assertNotNull(parsedRevisions);
    }

    @Test
    public void extractDataTestNoItems() throws openInputStreamException, IOException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("ridePositionSample.json");
        assert sampleFile != null;
        RidePositionParser ridePositionParserInstance = new RidePositionParser(new ApiInputStream(sampleFile));

        //Closed to test file exception
        sampleFile.close();

        try {
            ridePositionParserInstance.extractData(sampleFile);
            fail("Expected noItemFoundException");
        }catch (noItemFoundException e){
            assertEquals("Could not find the item you were looking for!",e.getMessage());
        }
    }

    @Test
    public void parseTest() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("ridePositionSample.json");
        assert sampleFile != null;
        RidePositionParser ridePositionParserInstance = new RidePositionParser(new ApiInputStream(sampleFile));
        assertInstanceOf(Coordinates.class,ridePositionParserInstance.parse());
    }

}
