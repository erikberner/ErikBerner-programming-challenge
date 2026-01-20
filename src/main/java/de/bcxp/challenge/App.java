package de.bcxp.challenge;

import de.bcxp.challenge.common.dataAccess.impl.RepositoryCSV;
import de.bcxp.challenge.common.dataAccess.api.Repository;
import de.bcxp.challenge.country.model.Country;
import de.bcxp.challenge.weather.model.WeatherDay;

import java.util.Comparator;
import java.util.List;

public final class App {

    public static void main(String... args) {

        // --- TASK 1: Weather ---

        // CSV with comma separator
        Repository<WeatherDay> weatherRepo = new RepositoryCSV<>(",", parts -> {
            if (parts.length < 3) return null;
            return new WeatherDay(
                    Integer.parseInt(parts[0]),
                    Double.parseDouble(parts[1]),
                    Double.parseDouble(parts[2])
            );
        });

        List<WeatherDay> weatherDays = weatherRepo.read("weather.csv");

        String dayWithSmallestTempSpread = weatherDays.stream()
                .min(Comparator.comparingDouble(WeatherDay::getSpread))
                .map(day -> String.format("Day %d (Spread: %.1f)", day.getDay(), day.getSpread()))
                .orElse("Data not found");

        System.out.printf("Day with smallest temperature spread: %s%n", dayWithSmallestTempSpread);



        // --- TASK 2: Countries ---

        System.out.println("--------------------------------------------------");

        Repository<Country> countryRepo = new RepositoryCSV<>(";", parts -> {
            if (parts.length < 5) return null;

            String name = parts[0];

            // replacing , with . to be able to parse it as a double after removing all the existing .'s
            String popString = parts[3].replace(".", "").replace(",", ".");
            String areaString = parts[4].replace(".", "").replace(",", ".");

            double popDouble = Double.parseDouble(popString);
            double area = Double.parseDouble(areaString);

            return new Country(name, (long) popDouble, area);
        });

        List<Country> countries = countryRepo.read("countries.csv");

        String countryWithHighestDensity = countries.stream()
                .max(Comparator.comparingDouble(Country::getPopulationDensity))
                .map(c -> String.format("%s (Density: %.2f)", c.getName(), c.getPopulationDensity()))
                .orElse("Data not found");

        System.out.printf("Country with highest population density: %s%n", countryWithHighestDensity);
    }
}