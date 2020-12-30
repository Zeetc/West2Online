package service;

import domain.Country;

import java.util.List;

public interface CountryService {
    public String getCountryJson(String countryName);

    public Country getCountryByWeb(String countryName);

    public List<Country> getAllCountry();

    public void updateCountry(Country country);

    public void insertAllCountry();

    public void insertCountry(Country country);

    public Country getCountry(String countryName);
}
