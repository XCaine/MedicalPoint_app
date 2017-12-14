package com.medical.service;

import com.medical.dao.CountryDao;
import com.medical.domain.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("countryService")
@Transactional
public class CountryServiceImpl extends GenericServiceImpl<Country> implements CountryService{

   /* @Autowired
    CountryServiceImpl(CountryDao countryDao){
        super(countryDao);
    }*/

    @Autowired
    CountryDao countryDao;

    @Override
    public void add(Country country) { countryDao.save(country); }

    @Override
    public void update(Country country) {
countryDao.saveOrUpdate(country);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Country findByName(String countryName) {
        return countryDao.findByName(countryName);
    }

    @Override
    public void removeAll() {
        countryDao.deleteAll();
    }


}
