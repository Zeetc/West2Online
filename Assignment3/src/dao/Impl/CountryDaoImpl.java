package dao.Impl;

import dao.CountryDao;
import domain.Country;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl implements CountryDao {
    private static JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public void insertCountry(Country country) {
        String sql="insert into country(ID,Name,sq_km_area,capital_city,continent,location,abbreviation,confirmed,recovered,deaths) values(?,?,?,?,?,?,?,?,?,?)";
        template.update(sql,country.getId(),country.getName(),country.getSq_km_area(),country.getCapital_city(),
                country.getContinent(),country.getLocation(),country.getAbbreviation(),
                country.getConfirmed(),country.getRecovered(),country.getDeaths()
        );
    }

    @Override
    public void updateCountry(Country country) {
        String sql="update country set sq_km_area = ? , capital_city = ? ,continent = ? ," +
                    "location = ? , abbreviation = ? , confirmed = ? , " +
                "recovered = ? , deaths = ? " +
                "where ID=?";
        template.update(sql,country.getSq_km_area(),country.getCapital_city(),
                country.getContinent(),country.getLocation(),country.getAbbreviation(),
                country.getConfirmed(),country.getRecovered(),country.getDeaths(),
                country.getId()
        );
    }

    @Override
    public List<Country> getAll() {
        String sql="select * from country";
        List<Country> countries=new ArrayList<>();
        try{
            countries=template.query(sql,new BeanPropertyRowMapper<>(Country.class));
        }catch (Exception e){
            //e.printStackTrace();
        }
        return countries;
    }

    @Override
    public Country getCountry(String name) {
        String sql="select * from country where name = ?";
        Country country=null;
        try{
            country=template.queryForObject(sql,new BeanPropertyRowMapper<>(Country.class),name);
        }catch (Exception e){
            //e.printStackTrace();
        }
        return country;
    }
}
