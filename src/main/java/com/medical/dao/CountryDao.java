package com.medical.dao;

import com.medical.domain.Country;
import com.medical.domain.Province;

public interface CountryDao extends GenericDao<Country> {

    Country findByName(String countryName);
    Province findProvinceInCountryByName(String provinceName, Country country);
}
