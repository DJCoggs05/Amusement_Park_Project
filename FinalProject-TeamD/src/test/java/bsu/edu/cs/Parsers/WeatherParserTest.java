package bsu.edu.cs.Parsers;

import bsu.edu.cs.Exceptions.noItemFoundException;
import bsu.edu.cs.Exceptions.openInputStreamException;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class WeatherParserTest {

    @Test
    public void convertDataTestGetFeelsLike() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("weatherSample.json");
        assert sampleFile != null;
        WeatherParser weatherParserInstance = new WeatherParser(new ApiInputStream(sampleFile));
        assertEquals(79.75,weatherParserInstance.parse().getFeels_like());
    }

    @Test
    public void convertDataTestGetTemp() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("weatherSample.json");
        assert sampleFile != null;
        WeatherParser weatherParserInstance = new WeatherParser(new ApiInputStream(sampleFile));
        assertEquals(80.2,weatherParserInstance.parse().getTemperature());
    }

    @Test
    public void convertDataTestGetWindSpeed() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("weatherSample.json");
        assert sampleFile != null;
        WeatherParser weatherParserInstance = new WeatherParser(new ApiInputStream(sampleFile));
        assertEquals(3,weatherParserInstance.parse().getWindSpeed());
    }

    @Test
    public void convertDataTestGetHumidity() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("weatherSample.json");
        assert sampleFile != null;
        WeatherParser weatherParserInstance = new WeatherParser(new ApiInputStream(sampleFile));
        assertEquals(36,weatherParserInstance.parse().getHumidity());
    }

    @Test
    public void convertDataTestGetID() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("weatherSample.json");
        assert sampleFile != null;
        WeatherParser weatherParserInstance = new WeatherParser(new ApiInputStream(sampleFile));
        assertEquals("01d",weatherParserInstance.parse().getIconID());
    }

    @Test
    public void getQueryTest() throws openInputStreamException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("weatherSample.json");
        assert sampleFile != null;
        WeatherParser weatherParserInstance = new WeatherParser(new ApiInputStream(sampleFile));
        assertEquals("$..*",weatherParserInstance.getQuery());
    }

    @Test
    public void parseTest() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("weatherSample.json");
        assert sampleFile != null;
        WeatherParser weatherParserInstance = new WeatherParser(new ApiInputStream(sampleFile));
        assertInstanceOf(Weather.class,weatherParserInstance.parse());
    }

    @Test
    public void extractDataTest() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("weatherSample.json");
        assert sampleFile != null;
        WeatherParser weatherParserInstance = new WeatherParser(new ApiInputStream(sampleFile));
        JSONArray parsedData = weatherParserInstance.extractData(new ByteArrayInputStream(weatherParserInstance.inputStreamInstance.inputStream));
        assertNotNull(parsedData);
    }

    @Test
    public void extractDataTestNoItems() throws openInputStreamException, IOException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("parks.json");
        assert sampleFile != null;
        WeatherParser weatherParserInstance = new WeatherParser(new ApiInputStream(sampleFile));

        //Closed to test file exception
        sampleFile.close();

        try {
            weatherParserInstance.extractData(sampleFile);
            fail("Expected noItemFoundException");
        }catch (noItemFoundException e){
            assertEquals("Could not find the item you were looking for!",e.getMessage());
        }
    }

}