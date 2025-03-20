package controller;

import model.DataProcessor;
import java.io.IOException;

public class DataController {
    private DataProcessor dataProcessor;

    public DataController() {
        try {
            dataProcessor = new DataProcessor("data/spotify_data.csv");
        } catch (IOException e) {
            System.err.println("Error reading CSV: " + e.getMessage());
        }
    }

    public DataProcessor getDataProcessor() {
        return dataProcessor;
    }
}
