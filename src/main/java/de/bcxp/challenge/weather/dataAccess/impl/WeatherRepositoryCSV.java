package de.bcxp.challenge.weather.dataAccess.impl;

import de.bcxp.challenge.weather.dataAccess.api.WeatherRepository;
import de.bcxp.challenge.weather.model.WeatherDay;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class WeatherRepositoryCSV implements WeatherRepository {

    @Override
    public List<WeatherDay> readWeather(String fileName) {
        List<WeatherDay> days = new ArrayList<>();

        String resourcePath = "/de/bcxp/challenge/" + fileName;

        // Use try-with-resources to ensure the InputStream is closed automatically
        try (InputStream is = getClass().getResourceAsStream(resourcePath)) {

            // Ensure the file actually exists
            if (is == null) {
                System.err.println("File not found: " + resourcePath);
                return days;
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                String line;
                boolean isHeader = true;

                while ((line = reader.readLine()) != null) {
                    // Skip the first line (CSV Header)
                    if (isHeader) {
                        isHeader = false;
                        continue;
                    }

                    // Split the line by comma
                    String[] parts = line.split(",");

                    // Basic validation: Ensure we have enough columns (Day, MxT, MnT)
                    if (parts.length >= 3) {
                        try {
                            // Parse data: Column 0 = Day, 1 = MaxTemp, 2 = MinTemp
                            int day = Integer.parseInt(parts[0]);
                            double max = Double.parseDouble(parts[1]);
                            double min = Double.parseDouble(parts[2]);

                            days.add(new WeatherDay(day, max, min));
                        } catch (NumberFormatException e) {
                            // Log invalid data but continue processing other rows
                            System.err.println("Skipping invalid row (parsing error): " + line);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return days;
    }
}