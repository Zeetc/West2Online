package service;

import domain.City;
import domain.Country;

import java.util.List;

public interface CityService {
    public List<City> getAllCity(Country country);

    public City getCity(Country country,String name);

    public City getCityByWeb(Country country,String name);

    public List<City> getAllCityByWeb(Country country);

    public void insertAll(List<City> cities);

    public void insertCity(City city);

    public void updateCity(City city);

    public void updateAllCityByWeb(Country country);
}
