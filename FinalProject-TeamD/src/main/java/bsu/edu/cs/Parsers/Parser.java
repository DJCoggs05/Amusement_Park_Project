package bsu.edu.cs.Parsers;

import bsu.edu.cs.Exceptions.noItemFoundException;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public abstract class Parser <T> {

    protected ApiInputStream inputStreamInstance;

    public Parser(ApiInputStream inputStream){
        this.inputStreamInstance = inputStream;
    }

    public T parse() throws noItemFoundException {
        JSONArray parsedRevisions = extractData(new ByteArrayInputStream(this.inputStreamInstance.inputStream));
        return convertData(parsedRevisions);
    }

    protected JSONArray extractData(InputStream inputStreamInstance) throws noItemFoundException {
        JSONArray dataArray;
        try {
             dataArray = JsonPath.read(inputStreamInstance,getQuery());
        }catch (Exception e) {
            throw new noItemFoundException();
        }
        return dataArray;
    }

    protected abstract String getQuery();
    protected abstract T convertData(JSONArray list);
}
