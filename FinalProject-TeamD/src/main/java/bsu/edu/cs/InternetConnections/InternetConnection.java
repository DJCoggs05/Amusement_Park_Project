package bsu.edu.cs.InternetConnections;

import bsu.edu.cs.Exceptions.networkErrorException;
import com.jayway.jsonpath.JsonPath;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public abstract class InternetConnection<T> {
    public InputStream search(T searchItem) throws networkErrorException {
        String url = createRequestUrl(searchItem);
        return getInputStream(url);
    }

    protected InputStream getInputStream(String url) throws networkErrorException {
        InputStream output;
        try{
            @SuppressWarnings("deprecation")
            URL urlConnection = new URL(url);
            URLConnection connectionInstance = urlConnection.openConnection();
            connectionInstance.setRequestProperty("User-Agent",
                    "Revision Reporter/0.1 (nolan.meyer@bsu.edu)");
            output = connectionInstance.getInputStream();
        }catch (Exception e) {
            throw new networkErrorException();
        }
        return output;
    }

    protected static String loadApiKey(String key) throws IOException {
        InputStream apiKeyFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("ApiKeys.json");
        return JsonPath.read(apiKeyFile,key);
    }

    protected abstract String createRequestUrl(T searchItem);

}
