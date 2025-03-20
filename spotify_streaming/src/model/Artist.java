package model;

public class Artist {
    private String name;
    private int totalStreams;

    public Artist(String name, int totalStreams) {
        this.name = name;
        this.totalStreams = totalStreams;
    }

    public String getName() { return name; }
    public int getTotalStreams() { return totalStreams; }
}