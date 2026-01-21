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
        Repository<WeatherDay> weatherRepo = new RepositoryCSV<>(",", new WeatherMapper());
        AnalysisService weatherService = new WeatherService(weatherRepo);

        String weatherResult = weatherService.analyze("weather.csv");
        System.out.printf("Day with smallest temperature spread: %s%n", weatherResult);

        System.out.println("--------------------------------------------------");

        // --- TASK 2: Countries ---
        Repository<Country> countryRepo = new RepositoryCSV<>(";", new CountryMapper());
        AnalysisService countryService = new CountryService(countryRepo);

        String countryResult = countryService.analyze("countries.csv");
        System.out.printf("Country with highest population density: %s%n", countryResult);
    }
}