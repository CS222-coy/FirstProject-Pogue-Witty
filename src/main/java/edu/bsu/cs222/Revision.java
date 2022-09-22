package edu.bsu.cs222;

public class Revision {
    public Revision(String username, String timestamp){
        this.username = username;
        this.timestamp = timestamp;
    }

    private final String username;

    private final String timestamp;

    public String getTimestamp() {
        return timestamp;
    }

    public String getUsername() {
        return username;
    }
}
