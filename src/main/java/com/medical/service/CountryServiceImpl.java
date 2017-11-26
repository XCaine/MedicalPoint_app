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
        countryDao.save(country);
    }

    @Override
    public void saveOrUpdate(Country country) {
    countryDao.saveOrUpdate(country);
    }

    @Override
    public void update(Country country) {
countryDao.saveOrUpdate(country);
    }

    @Override
    public void remove(Country country) {
countryDao.remove(country);
    }

    @Override
    public Country find(Integer id) {
       return countryDao.findById(id);
    }

    @Override
    public List<Country> findALL() {
        return countryDao.findAll();
    }

    @Override
    public Country findByName(String countryName) {
        return countryDao.findByName(countryName);
    }

    @Override
    public void removeAll() {
        countryDao.deleteAll();
    }
}
