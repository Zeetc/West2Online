package dao;

import domain.City;
import domain.Country;

import java.util.List;

public interface CityDao {
    public City getCity(Country country,String name);

    public City getCity(int cid,String name);

    List<City> getAllCity(Country country);

    public void insertCity(City city);

    public void updateCity(City city);
}
