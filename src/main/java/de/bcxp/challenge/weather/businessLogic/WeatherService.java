package de.bcxp.challenge.weather.businessLogic;

import de.bcxp.challenge.common.businessLogic.AnalysisService;
import de.bcxp.challenge.common.dataAccess.api.Repository;
import de.bcxp.challenge.weather.model.WeatherDay;

import java.util.Comparator;
import java.util.List;

public class WeatherService implements AnalysisService {

    private final Repository<WeatherDay> repository;

    public WeatherService(Repository<WeatherDay> repository) {
        this.repository = repository;
    }

    @Override
    public String analyze(String fileName) {
        List<WeatherDay> days = repository.read(fileName);

        return days.stream()
                .min(Comparator.comparingDouble(WeatherDay::getSpread))
                .map(day -> String.format("Day %d (Spread: %.1f)", day.getDay(), day.getSpread()))
                .orElse("Data not found");
    }
}