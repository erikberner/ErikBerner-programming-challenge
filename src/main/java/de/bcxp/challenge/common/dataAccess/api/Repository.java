package de.bcxp.challenge.common.dataAccess.api;

import java.util.List;

/**
 * Generic interface for data access.
 * @param <T> The type of the domain model (e.g. WeatherDay, Country).
 */
public interface Repository<T> {
    /**
     * Reads data from a given source.
     * @param source The source identifier (e.g. filename).
     * @return A list of objects of type T.
     */
    List<T> read(String source);
}