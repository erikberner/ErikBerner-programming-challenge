package de.bcxp.challenge.country;

import de.bcxp.challenge.country.model.Country;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CountryTest {

    @Test
    void testPopulationDensity() {
        Country country = new Country("Testland", 1000, 10.0);
        double density = country.getPopulationDensity();

        // Then: 1000 / 10 = 100
        assertEquals(100.0, density, 0.0001);
    }

    @Test
    void testDensityWithZeroArea() {
        // a country with 0 area
        Country country = new Country("NoLand", 1000, 0.0);
        double density = country.getPopulationDensity();

        // Then: returns 0 but doesn't crash
        assertEquals(0.0, density, "area 0 should lead to a density of 0");
    }
}