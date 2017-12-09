package com.medical.dao;

import com.medical.domain.Country;
import com.medical.domain.Province;

import java.util.List;

public interface CountryDao extends GenericDao<Country> {

    public Country findByName(String countryName);
    public Province findProvinceInCountryByName(String provinceName, Country country);
}
