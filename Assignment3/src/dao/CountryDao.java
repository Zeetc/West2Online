package dao;

import domain.Country;

import java.util.List;

public interface CountryDao {
    public void insertCountry(Country country);

    public void updateCountry(Country country);

    public List<Country> getAll();

    public Country getCountry(String name);
}
