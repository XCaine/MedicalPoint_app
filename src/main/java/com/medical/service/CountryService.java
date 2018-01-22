package com.medical.service;

import com.medical.domain.Country;

public interface CountryService extends GenericService<Country>{

    void add(Country country);

    void update(Country country);

    void removeAll();

    Country findByName(String countryName);

    //public String getTableName();
}
