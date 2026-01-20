package de.bcxp.challenge.weather.model;

public class WeatherDay {
    private final int day;
    private final double maxTemp;
    private final double minTemp;

    public WeatherDay(int day, double maxTemp, double minTemp) {
        this.day = day;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    public int getDay() {
        return day;
    }

    //calculations
    public double getSpread() {
        return maxTemp - minTemp;
    }
}