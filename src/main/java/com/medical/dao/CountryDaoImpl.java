package com.medical.dao;

import com.medical.domain.Country;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;


@Repository("countryDao")
public class CountryDaoImpl extends AbstractGenericDao<Country> implements CountryDao {

    @Override
    public Country findByName(String countryName) {
        Query query = currentSession().createQuery("from Country " + "where name=:name");
        query.setParameter("name", countryName);
        return (Country) query.uniqueResult();
    }
}