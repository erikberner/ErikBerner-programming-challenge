package de.bcxp.challenge.weather.mapper;

import de.bcxp.challenge.weather.model.WeatherDay;
import java.util.function.Function;

public class WeatherMapper implements Function<String[], WeatherDay> {

    @Override
    public WeatherDay apply(String[] parts) {
        if (parts.length < 3) return null;

        return new WeatherDay(
                Integer.parseInt(parts[0]),
                Double.parseDouble(parts[1]),
                Double.parseDouble(parts[2])
        );
    }
}