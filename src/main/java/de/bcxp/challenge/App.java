package de.bcxp.challenge;

import de.bcxp.challenge.weather.dataAccess.api.WeatherRepository;
import de.bcxp.challenge.weather.dataAccess.impl.WeatherRepositoryCSV;
import de.bcxp.challenge.weather.model.WeatherDay;

import java.util.Comparator;
import java.util.List;

/**
 * The entry class for your solution.
 */
public final class App {

    public static void main(String... args) {

        // --- Task 1: weather ---

        WeatherRepository weatherRepository = new WeatherRepositoryCSV();

        List<WeatherDay> weatherDays = weatherRepository.readWeather("weather.csv");

        String dayWithSmallestTempSpread = weatherDays.stream()
                .min(Comparator.comparingDouble(WeatherDay::getSpread))
                .map(day -> String.format("Day %d (Spread: %.1f)", day.getDay(), day.getSpread()))
                .orElse("Data not found");

        System.out.printf("Day with smallest temperature spread: %s%n", dayWithSmallestTempSpread);


        String countryWithHighestPopulationDensity = "Some country"; // Your population density analysis function call …
        System.out.printf("Country with highest population density: %s%n", countryWithHighestPopulationDensity);
    }
}