package com.medical.service;

import com.medical.dao.CountryDao;
import com.medical.domain.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("countryService")
@Transactional
public class CountryServiceImpl implements CountryService{

    @Autowired
    private CountryDao countryDao;

    @Override
    public void add(Country country) {
        countryDao.add(country);
    }

    @Override
    public void saveOrUpdate(Country country) {
    countryDao.saveOrUpdate(country);
    }

    @Override
    public void update(Country country) {
countryDao.update(country);
    }

    @Override
    public void remove(Country country) {
countryDao.remove(country);
    }

    @Override
    public Country find(Integer id) {
       return countryDao.find(id);
    }

    @Override
    public List<Country> findALL() {
        return countryDao.findALL();
    }

    @Override
    public Country findByName(String countryName) {
        return countryDao.findByName(countryName);
    }
}
