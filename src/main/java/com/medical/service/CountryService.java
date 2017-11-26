package com.medical.service;

import com.medical.domain.Country;

import java.util.List;

public interface CountryService {

    public void add(Country country);


    public void saveOrUpdate(Country country);



    public void update(Country country);


    public void remove(Country country);

    public Country find(Integer id);

    public List<Country> findALL();

    public Country findByName(String countryName);
}
