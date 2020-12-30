package service.Impl;

import dao.CityDao;
import dao.Impl.CityDaoImpl;
import domain.City;
import domain.Country;
import net.sf.json.JSONObject;
import service.CityService;
import service.CountryService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CityServiceImpl implements CityService {
    private static CountryService countryService=new CountryServiceImpl();
    private static CityDao cityDao=new CityDaoImpl();

    @Override
    public List<City> getAllCity(Country country) {
        return cityDao.getAllCity(country);
    }

    @Override
    public City getCity(Country country, String name) {
        return cityDao.getCity(country,name);
    }

    @Override
    public City getCityByWeb(Country country, String name) {
        String countryJson = countryService.getCountryJson(country.getName());
        JSONObject jsonInfo = new JSONObject(countryJson);
        JSONObject o=new JSONObject(jsonInfo.getString(name));
        //int id, int cid, String name, int confirmed, int recovered, int deaths
        return constructCityByJson(country,o,name);
    }

    @Override
    public List<City> getAllCityByWeb(Country country) {
        List<City> cities=new ArrayList<>();
        String countryJson = countryService.getCountryJson(country.getName());
        JSONObject jsonObject=new JSONObject(countryJson);
        Iterator it= jsonObject.keys();
        while(it.hasNext()){
            String name=(String) it.next();
            if(name.equals("All"))continue;
            String s=  jsonObject.getString(name);
            JSONObject o=new JSONObject(s);
            cities.add(constructCityByJson(country,o,name));
        }
        return cities;
    }

    @Override
    public void insertAll(List<City> cities) {
        for(City city:cities){
            City checkExist=cityDao.getCity(city.getCid(),city.getName());
            if(checkExist==null)insertCity(city);
            else updateCity(city);
        }
    }

    @Override
    public void insertCity(City city) {
        cityDao.insertCity(city);
    }

    @Override
    public void updateCity(City city) {
        cityDao.updateCity(city);
    }

    @Override
    public void updateAllCityByWeb(Country country) {
        List<City> cities=getAllCityByWeb(country);
        for(City city:cities){
            updateCity(city);
        }
    }

    public City constructCityByJson(Country country,JSONObject o,String name){
        return new City(-1,country.getId(),name,o.getInt("confirmed"),o.getInt("recovered"),o.getInt("deaths"));
    }
}
