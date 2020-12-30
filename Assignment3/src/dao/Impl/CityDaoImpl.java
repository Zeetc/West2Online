package dao.Impl;

import dao.CityDao;
import domain.City;
import domain.Country;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

public class CityDaoImpl implements CityDao {
    private static JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public City getCity(Country country, String name) {
        String sql="select * from city where cid=? and name=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<City>(City.class),country.getId(),name);
    }

    @Override
    public City getCity(int cid, String name) {
        String sql="select * from city where cid=? and name=?";
        City city=null;
        try{
            city=template.queryForObject(sql,new BeanPropertyRowMapper<City>(City.class),cid,name);
        }catch (Exception e){

        }
        return city;
    }

    @Override
    public List<City> getAllCity(Country country) {
        String sql="select * from city where cid=?";
        return template.query(sql,new BeanPropertyRowMapper<City>(City.class),country.getId());
    }

    @Override
    public void insertCity(City city) {
        String sql = "insert into city(Name,cid,confirmed,recovered,deaths) values(?,?,?,?,?)";
        template.update(sql, city.getName(), city.getCid(), city.getConfirmed(), city.getRecovered(), city.getDeaths());
    }

    @Override
    public void updateCity(City city) {
        String sql="update city set confirmed = ?,recovered=?,deaths=? where id=? and cid=?";
        template.update(sql,city.getConfirmed(),city.getRecovered(),city.getRecovered(),city.getId(),city.getCid());
    }
}
