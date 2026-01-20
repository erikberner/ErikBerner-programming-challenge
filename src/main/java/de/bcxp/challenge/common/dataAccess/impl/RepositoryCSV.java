package de.bcxp.challenge.common.dataAccess.impl;

import de.bcxp.challenge.common.dataAccess.api.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * A generic CSV implementation of the Repository interface.
 */
public class RepositoryCSV<T> implements Repository<T> {

    private final String separator;
    private final Function<String[], T> mapper;

    public RepositoryCSV(String separator, Function<String[], T> mapper) {
        this.separator = separator;
        this.mapper = mapper;
    }

    @Override
    public List<T> read(String fileName) {
        List<T> items = new ArrayList<>();
        String resourcePath = "/de/bcxp/challenge/" + fileName;

        try (InputStream is = getClass().getResourceAsStream(resourcePath)) {
            if (is == null) {
                System.err.println("File not found: " + resourcePath);
                return items;
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                String line;
                boolean isHeader = true;

                while ((line = reader.readLine()) != null) {
                    if (isHeader) {
                        isHeader = false;
                        continue;
                    }

                    String[] parts = line.split(separator);

                    try {
                        // Use the mapper function to convert the String array to object T
                        T item = mapper.apply(parts);
                        if (item != null) {
                            items.add(item);
                        }
                    } catch (Exception e) {
                        System.err.println("Skipping invalid row: " + line + " Error: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }
}