package com.medical.dao;

import com.medical.domain.City;
import com.medical.domain.Country;
import com.medical.domain.MedicalPoint;
import com.medical.domain.Province;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


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
}
