package de.bcxp.challenge.country.model;

public class Country {

    private final String name;
    private final long population;
    private final double area;

    public Country(String name, long population, double area) {
        this.name = name;
        this.population = population;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public long getPopulation() {
        return population;
    }

    public double getArea() {
        return area;
    }

    //calculations
    public double getPopulationDensity() {
        if (area == 0) {
            return 0.0;
        }
        return (double) population / area;
    }
}