package controller;

import model.Song;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.util.*;

public class VisualizationController extends JFrame {
    private final DataController dataController;

    public VisualizationController() {
        dataController = new DataController();
        setTitle("Spotify Data Analysis");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void displayCharts() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Top 10 Songs", new ChartPanel(createTop10Chart()));
        tabbedPane.add("Genre Popularity", new ChartPanel(createGenreChart()));
        tabbedPane.add("Artist Streams", new ChartPanel(createArtistChart()));
        tabbedPane.add("Streaming Trends", new ChartPanel(createTrendChart()));
        add(tabbedPane);
        setVisible(true);
    }

    private JFreeChart createTop10Chart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Song> topSongs = dataController.getDataProcessor().getTop10Songs();
        for (Song song : topSongs) {
            dataset.addValue(song.getStreams(), song.getArtist(), song.getName());
        }
        return ChartFactory.createBarChart("Top 10 Songs", "Song", "Streams", dataset, PlotOrientation.VERTICAL, true, true, false);
    }

    private JFreeChart createGenreChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        Map<String, Integer> genreData = dataController.getDataProcessor().getGenrePopularity();
        genreData.forEach(dataset::setValue);
        return ChartFactory.createPieChart("Genre Popularity", dataset, true, true, false);
    }

    private JFreeChart createArtistChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<String, Integer> artistData = dataController.getDataProcessor().getArtistStreams();
        artistData.forEach((artist, streams) -> dataset.addValue(streams, artist, artist));
        return ChartFactory.createBarChart("Artist Streams", "Artist", "Streams", dataset);
    }

    private JFreeChart createTrendChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<String, Integer> trendData = dataController.getDataProcessor().getStreamingTrends();
        trendData.forEach((song, streams) -> dataset.addValue(streams, "Streams", song));
        return ChartFactory.createLineChart("Streaming Trends", "Song", "Streams", dataset);
    }
}
