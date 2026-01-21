package de.bcxp.challenge;

import de.bcxp.challenge.common.businessLogic.AnalysisService;
import de.bcxp.challenge.common.dataAccess.impl.RepositoryCSV;
import de.bcxp.challenge.common.dataAccess.api.Repository;
import de.bcxp.challenge.country.businessLogic.CountryService;
import de.bcxp.challenge.country.mapper.CountryMapper;
import de.bcxp.challenge.country.model.Country;
import de.bcxp.challenge.weather.businessLogic.WeatherService;
import de.bcxp.challenge.weather.mapper.WeatherMapper;
import de.bcxp.challenge.weather.model.WeatherDay;

public final class App {

    public static void main(String... args) {

        // --- TASK 1: Weather ---

        // 1. SETUP
        // Wir übergeben einfach eine neue Instanz des Mappers!
        Repository<WeatherDay> weatherRepo = new RepositoryCSV<>(",", new WeatherMapper());
        Repository<Country> countryRepo = new RepositoryCSV<>(";", new CountryMapper());

        // 2. SERVICES
        AnalysisService weatherService = new WeatherService(weatherRepo);
        AnalysisService countryService = new CountryService(countryRepo);

        // 3. EXECUTION
        String weatherResult = weatherService.analyze("weather.csv");
        System.out.printf("Day with smallest temperature spread: %s%n", weatherResult);

        System.out.println("--------------------------------------------------");

        String countryResult = countryService.analyze("countries.csv");
        System.out.printf("Country with highest population density: %s%n", countryResult);
    }
}