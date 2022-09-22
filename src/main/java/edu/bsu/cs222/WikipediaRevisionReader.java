package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Scanner;

public class WikipediaRevisionReader {
    public static void main(String[] args) {
        WikipediaRevisionReader revisionReader = new WikipediaRevisionReader();
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        try{
            String timestamp = revisionReader.getLatestRevision(line);
            System.out.println(timestamp);
        } catch (IOException ioException){
            System.err.println("Network connection problem: " + ioException.getMessage());
        }
    }
    private String getLatestRevision(String articleTitle) throws IOException{
        String urlString = String.format("https://en.wikipedia.org/w/api.php?action=query&format=json&requestid=&prop=revisions&titles=%s&redirects=1&rvprop=timestamp%7Cuser&rvlimit=30\n",
                articleTitle);
        String encoderURLString = URLEncoder.encode(urlString, Charset.defaultCharset());
        try{
            URL url = new URL(encoderURLString);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "Revision Reporter/0.1 " +
                    "(http://www.cs.bsu.edu/~pvg/courses/cs222Sp22; gjwitty@bsu.edu) ");
            InputStream inputStream = connection.getInputStream();
            WikipediaRevisionParser parser = new WikipediaRevisionParser();
            //Lines 30-35 cannot be tested, does not follow FIRST (Not Independent)
            String timestamp = parser.parse(inputStream);
            return timestamp;
        } catch (MalformedURLException malformedURLException){
            throw new RuntimeException(malformedURLException);
        }
    }
}
