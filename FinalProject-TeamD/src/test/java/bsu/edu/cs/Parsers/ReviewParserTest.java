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

public class ReviewParserTest {

    @Test
    public void getQueryTest() throws openInputStreamException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("reviewSample.json");
        assert sampleFile != null;
        ReviewParser reviewParserInstance= new ReviewParser(new ApiInputStream(sampleFile));
        assertEquals("$.result[*]",reviewParserInstance.getQuery());
    }

    @Test
    public void convertDataTest() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("reviewSample.json");
        assert sampleFile != null;
        ReviewParser reviewParserInstance= new ReviewParser(new ApiInputStream(sampleFile));
        JSONArray parsedData = reviewParserInstance.extractData(new ByteArrayInputStream(reviewParserInstance.inputStreamInstance.inputStream));
        ReviewInformation convertedObject = reviewParserInstance.convertData(parsedData);
        assertEquals(5,convertedObject.getListOfReviews().size());
    }

    @Test
    public void extractDataTest() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("reviewSample.json");
        assert sampleFile != null;
        ReviewParser reviewParserInstance= new ReviewParser(new ApiInputStream(sampleFile));
        JSONArray parsedData = reviewParserInstance.extractData(new ByteArrayInputStream(reviewParserInstance.inputStreamInstance.inputStream));
        assertNotNull(parsedData);
    }

    @Test
    public void extractDataTestNoItems() throws openInputStreamException, IOException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("reviewSample.json");
        assert sampleFile != null;
        ReviewParser reviewParserInstance= new ReviewParser(new ApiInputStream(sampleFile));

        //Closed to test file exception
        sampleFile.close();

        try {
            reviewParserInstance.extractData(sampleFile);
            fail("Expected noItemFoundException");
        }catch (noItemFoundException e){
            assertEquals("Could not find the item you were looking for!",e.getMessage());
        }
    }

    @Test
    public void parseTest() throws openInputStreamException, noItemFoundException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("reviewSample.json");
        assert sampleFile != null;
        ReviewParser reviewParserInstance= new ReviewParser(new ApiInputStream(sampleFile));
        assertEquals(5,reviewParserInstance.parse().getListOfReviews().size());
    }
}
