package de.bcxp.challenge.common;

import de.bcxp.challenge.common.dataAccess.impl.RepositoryCSV;
import de.bcxp.challenge.weather.model.WeatherDay;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryCSVTest {

    @Test
    void testReadWeatherCsv() {
        // configuring the reader for weather
        RepositoryCSV<WeatherDay> reader = new RepositoryCSV<>(",", parts -> {
            // simple mapper
            return new WeatherDay(
                    Integer.parseInt(parts[0]),
                    Double.parseDouble(parts[1]),
                    Double.parseDouble(parts[2])
            );
        });

        List<WeatherDay> results = reader.read("weather.csv");
        assertFalse(results.isEmpty(), "Die Wetter-Liste sollte nicht leer sein");

        // Check: if first entry is correct
        assertEquals(1, results.get(0).getDay());
    }

    @Test
    void testReadNonExistentFile() {
        RepositoryCSV<String> reader = new RepositoryCSV<>(",", parts -> parts[0]);

        // When: file doesnt exist
        List<String> results = reader.read("not_available.csv");

        // Then: returns empty list
        assertTrue(results.isEmpty());
    }
}