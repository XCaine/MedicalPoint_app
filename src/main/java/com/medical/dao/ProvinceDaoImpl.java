package com.medical.dao;

import com.medical.domain.City;
import com.medical.domain.Province;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;


@Repository("provinceDao")
public class ProvinceDaoImpl extends AbstractGenericDao<Province> implements ProvinceDao{

    ProvinceDaoImpl(){super("Province");}

    public City findCityInProvinceByName(String cityName, Province province){
        Query query = currentSession().createQuery("from City C where C.name=:name and C.province.id=:id");
        query.setParameter("name", cityName);
        query.setParameter("id", province.getId());
        return (City) query.uniqueResult();
    }

    public void addCity(String cityName, Province province){
        City city = new City();
        city.setName(cityName);
        city.setProvince(province);
        currentSession().save(city);
    }

    public City findCityInProvince(City city){
        Query query = currentSession().createQuery("from City C where C.name =:cityName and C.province.name =:provinceName and C.province.country.name =:countryName");
        query.setParameter("cityName", city.getName());
        query.setParameter("provinceName", city.getProvince().getName());
        query.setParameter("countryName", city.getProvince().getCountry().getName());
        EntityGraph entityGraph = currentSession().getEntityGraph("city.province.country");
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return (City) query.uniqueResult();
    }
}
