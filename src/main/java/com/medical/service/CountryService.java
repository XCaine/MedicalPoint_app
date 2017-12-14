package com.medical.service;

import com.medical.domain.Country;

import java.util.List;

public interface CountryService extends GenericService<Country>{

    public void add(Country country);

    public void update(Country country);

    public void removeAll();

    public Country findByName(String countryName);

    //public String getTableName();
}
