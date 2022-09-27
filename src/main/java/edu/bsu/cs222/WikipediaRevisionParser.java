package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class WikipediaRevisionParser {
    private ArrayList<Revision> revisions = new ArrayList<>();

    public String parseTimestamp(InputStream testDataStream) throws IOException {
        JSONArray result = JsonPath.read(testDataStream, "$..timestamp");
        return result.get(0).toString();
    }

    public String parseUser(InputStream testDataStream) throws IOException {
        JSONArray result = JsonPath.read(testDataStream, "$..user");
        return result.get(0).toString();
    }

    public ArrayList<Revision> parse(InputStream articleTitle) throws IOException {
        int arrayLength = JsonPath.read(articleTitle, "$..user.length()");
        for (int i = 0; i <= arrayLength && i <= 30; i++) {
            Revision revision = new Revision(JsonPath.read(articleTitle, "$..user"), (JsonPath.read(articleTitle, "$..timestamp")));
            revisions.add(revision);
        }
        return revisions;
    }

    public boolean doesPageExist(int value) {
        return true;
    }
}



