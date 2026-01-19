package de.bcxp.challenge.weather.dataAccess.api;

import de.bcxp.challenge.weather.model.WeatherDay;
import java.util.List;

public interface WeatherRepository {
    List<WeatherDay> readWeather(String fileName);
}