package bsu.edu.cs.Parsers;

import bsu.edu.cs.Exceptions.openInputStreamException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ApiInputStream {
    protected byte[] inputStream;
    public ApiInputStream(InputStream inputStream) throws openInputStreamException {
        try{
            this.inputStream = inputStream.readAllBytes();
        }catch (Exception e) {
            throw new openInputStreamException();
        }
    }

    public ByteArrayInputStream openInputStream() {
        return new ByteArrayInputStream(inputStream);
    }
}