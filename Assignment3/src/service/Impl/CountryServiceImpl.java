package service.Impl;

import Get.CatchInfo;
import dao.CountryDao;
import dao.Impl.CountryDaoImpl;
import domain.Country;
import net.sf.json.JSONObject;
import service.CountryService;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CountryServiceImpl implements CountryService {
    private static CountryDao countryDao=new CountryDaoImpl();
    static Map<String,Integer> countryID=new HashMap<>();
    static {
        countryID.put("France",1);
        countryID.put("China",2);
        countryID.put("Japan",3);
        countryID.put("US",4);
        countryID.put("United Kingdom",5);
    }

    @Override
    public String getCountryJson(String countryName) {
        CatchInfo cat=new CatchInfo();
        //https://covid-api.mmediagroup.fr/v1/cases?country=United%20Kingdom
        String url="https://covid-api.mmediagroup.fr/v1/cases?country="+countryName;
        return cat.getJsonByURL(url);
    }

    @Override
    public Country getCountryByWeb(String countryName) {
        String json=getCountryJson(countryName);
        JSONObject jsonObject=new JSONObject(json);
        JSONObject o=new JSONObject(jsonObject.getString("All"));
        //int id, String name, int sq_km_area, String capital_city
        return new Country(countryID.get(countryName),countryName,o.getInt("sq_km_area"),o.getString("capital_city"),
                o.getString("continent"),o.getString("location"),o.getString("abbreviation"),o.getInt("confirmed"),
                o.getInt("recovered"),o.getInt("deaths"));
    }

    @Override
    public List<Country> getAllCountry() {
        return countryDao.getAll();
    }

    @Override
    public void updateCountry(Country country) {
        Country newCountry= getCountryByWeb(country.getName());
        countryDao.updateCountry(newCountry);
    }

    @Override
    public void insertAllCountry() {
        Iterator it=countryID.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry entry= (Map.Entry) it.next();
            String name= (String) entry.getKey();
            Country country=getCountryByWeb(name);
            insertCountry(country);
        }
    }

    @Override
    public void insertCountry(Country country) {
        Country checkExist=countryDao.getCountry(country.getName());
        if(checkExist==null) {
            countryDao.insertCountry(country);
        }else updateCountry(country);
    }

    @Override
    public Country getCountry(String countryName) {
        return countryDao.getCountry(countryName);
    }
}
