package bsu.edu.cs.Parsers;

import bsu.edu.cs.Exceptions.noItemFoundException;
import bsu.edu.cs.Exceptions.openInputStreamException;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ParkParserTest {

    @Test
    public void getQueryTest() throws openInputStreamException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("parks.json");
        assert sampleFile != null;
        ParkParser parkParserInstance = new ParkParser(new ApiInputStream(sampleFile));
        assertEquals("$..parks[*]",parkParserInstance.getQuery());
    }

    @Test
    public void convertDataTest() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("parks.json");
        assert sampleFile != null;
        ParkParser parkParserInstance = new ParkParser(new ApiInputStream(sampleFile));
        JSONArray parsedData = parkParserInstance.extractData(new ByteArrayInputStream(parkParserInstance.inputStreamInstance.inputStream));
        HashMap<String,Park> convertedList = parkParserInstance.convertData(parsedData);
        assertEquals(132, convertedList.size());
    }

    @Test
    public void extractDataTest() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("parks.json");
        assert sampleFile != null;
        ParkParser parkParserInstance = new ParkParser(new ApiInputStream(sampleFile));
        JSONArray parsedData = parkParserInstance.extractData(new ByteArrayInputStream(parkParserInstance.inputStreamInstance.inputStream));
        assertNotNull(parsedData);
    }

    @Test
    public void extractDataTestNoItems() throws openInputStreamException, IOException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("parks.json");
        assert sampleFile != null;
        ParkParser parkParserInstance = new ParkParser(new ApiInputStream(sampleFile));

        //Closed to test file exception
        sampleFile.close();

        try {
            parkParserInstance.extractData(sampleFile);
            fail("Expected noItemFoundException");
        }catch (noItemFoundException e){
            assertEquals("Could not find the item you were looking for!",e.getMessage());
        }
    }

    @Test
    public void parseTest() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("parks.json");
        assert sampleFile != null;
        ParkParser parkParserInstance = new ParkParser(new ApiInputStream(sampleFile));
        assertEquals(132,parkParserInstance.parse().size());
    }

}
