package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;

public class WikipediaRevisionParser {
    public String parse(InputStream testDataStream) throws IOException {
        JSONArray result = JsonPath.read(testDataStream, "$..timestamp");
        return result.get(0).toString();
    }

    public boolean doesPageExist(int value) {
        return true;
    }
}



