package bsu.edu.cs.Parsers;

import bsu.edu.cs.Exceptions.openInputStreamException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class ApiInputStreamTest {

    @Test
    public void noInputStreamTest() throws IOException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("parks.json");
        assert sampleFile != null;

        //Closed to test file exception
        sampleFile.close();

        try {
            //Variable is never used because we are simply trying to force an error to be thrown for testing
            ApiInputStream apiInputStream = new ApiInputStream(sampleFile);
            fail("Input stream exception not thrown");
        }catch (openInputStreamException e){
            assertEquals("Couldn't open input stream!",e.getMessage());
        }
    }

    @Test
    public void openInputStreamTest() throws openInputStreamException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("parks.json");
        ApiInputStream testApiInputStream = new ApiInputStream(sampleFile);
        assertNotNull(testApiInputStream.openInputStream());
    }

}
