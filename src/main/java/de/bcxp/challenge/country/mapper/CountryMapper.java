package de.bcxp.challenge.country.mapper;

import de.bcxp.challenge.country.model.Country;
import java.util.function.Function;

public class CountryMapper implements Function<String[], Country> {

    @Override
    public Country apply(String[] parts) {
        // Validierung
        if (parts.length < 5) {
            return null;
        }

        String name = parts[0];

        // Parsing Logik (gekapselt!)
        String popString = parts[3].replace(".", "").replace(",", ".");
        String areaString = parts[4].replace(".", "").replace(",", ".");

        double popDouble = Double.parseDouble(popString);
        double area = Double.parseDouble(areaString);

        return new Country(name, (long) popDouble, area);
    }
}