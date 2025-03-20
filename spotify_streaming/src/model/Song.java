package model;

public class Song {
    private String name;
    private String artist;
    private int streams;
    private String genre;

    public Song(String name, String artist, int streams, String genre) {
        this.name = name;
        this.artist = artist;
        this.streams = streams;
        this.genre = genre;
    }

    public String getName() { return name; }
    public String getArtist() { return artist; }
    public int getStreams() { return streams; }
    public String getGenre() { return genre; }
}