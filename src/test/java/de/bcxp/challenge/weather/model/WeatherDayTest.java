package de.bcxp.challenge.weather.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WeatherDayTest {

    @Test
    void testSpreadCalculation() {
        WeatherDay day = new WeatherDay(1, 30.0, 10.0);
        double spread = day.getSpread();

        // Then: 30 - 10 = 20
        assertEquals(20.0, spread, 0.0001, "spread should be the difference");
    }

    @Test
    void testSpreadWithNegativeTemperatures() {
        // a day with negative temperatures
        WeatherDay day = new WeatherDay(2, -5.0, -15.0);
        double spread = day.getSpread();

        // Then: -5 - (-15) = 10
        assertEquals(10.0, spread, 0.0001);
    }
}