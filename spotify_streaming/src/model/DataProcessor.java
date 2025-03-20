package model;

import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

public class DataProcessor {
    private List<Song> songs;

    public DataProcessor(String csvFilePath) throws IOException {
        songs = new ArrayList<>();
        readCSV(csvFilePath);
    }

    private void readCSV(String csvFilePath) throws IOException {
        try (Reader reader = new FileReader(csvFilePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {

            for (CSVRecord record : csvParser) {
                String name = record.get("track_name");
                String artist = record.get("artist(s)_name");
                int streams = Integer.parseInt(record.get("streams"));
                String genre = record.get("in_spotify_playlists");

                songs.add(new Song(name, artist, streams, genre));
            }
        }
    }

    public List<Song> getTop10Songs() {
        return songs.stream().sorted(Comparator.comparingInt(Song::getStreams).reversed()).limit(10).toList();
    }

    public Map<String, Integer> getGenrePopularity() {
        Map<String, Integer> genreMap = new HashMap<>();
        for (Song song : songs) {
            genreMap.put(song.getGenre(), genreMap.getOrDefault(song.getGenre(), 0) + song.getStreams());
        }
        return genreMap;
    }

    public Map<String, Integer> getArtistStreams() {
        Map<String, Integer> artistMap = new HashMap<>();
        for (Song song : songs) {
            artistMap.put(song.getArtist(), artistMap.getOrDefault(song.getArtist(), 0) + song.getStreams());
        }
        return artistMap;
    }

    public Map<String, Integer> getStreamingTrends() {
        Map<String, Integer> trendMap = new HashMap<>();
        for (Song song : songs) {
            trendMap.put(song.getName(), song.getStreams());
        }
        return trendMap;
    }
}