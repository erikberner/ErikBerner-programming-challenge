package de.bcxp.challenge.country.businessLogic;


import de.bcxp.challenge.common.businessLogic.AnalysisService;
import de.bcxp.challenge.common.dataAccess.api.Repository;
import de.bcxp.challenge.country.model.Country;

import java.util.Comparator;
import java.util.List;

public class CountryService implements AnalysisService {

    private final Repository<Country> repository;

    public CountryService(Repository<Country> repository) {
        this.repository = repository;
    }

    @Override
    public String analyze(String fileName) {
        List<Country> countries = repository.read(fileName);

        return countries.stream()
                .max(Comparator.comparingDouble(Country::getPopulationDensity))
                .map(c -> String.format("%s (Density: %.2f)", c.getName(), c.getPopulationDensity()))
                .orElse("Data not found");
    }
}