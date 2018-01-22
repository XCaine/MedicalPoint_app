package com.medical.dao;

import com.medical.domain.Country;
import com.medical.domain.Province;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("countryDao")
@Transactional
public class CountryDaoImpl extends AbstractGenericDao<Country> implements CountryDao {


    public CountryDaoImpl() {super("Country");}

    @Override
    public Country findByName(String countryName) {
        Query query = currentSession().createQuery("from Country C where C.name=:name");
        query.setParameter("name", countryName);
        return (Country) query.uniqueResult();
    }

    @Override
    public Province findProvinceInCountryByName(String provinceName, Country country){
        Query query = currentSession().createQuery("from Province P where P.name=:name and P.country.id=:id");
        query.setParameter("name", provinceName);
        query.setParameter("id", country.getId());
        return (Province) query.uniqueResult();
    }
}