package com.medical.dao;

import com.medical.domain.City;
import com.medical.domain.Country;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("cityDao")
public class CityDaoImpl extends AbstractGenericDao<City> implements CityDao{

    public CityDaoImpl(){super("City");}

    @Override
    public City findByName(String cityName) {
        Query query = currentSession().createQuery("from City " + "where name=:name");
        query.setParameter("name", cityName);
        return (City) query.uniqueResult();
    }
}
