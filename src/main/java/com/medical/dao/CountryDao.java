package com.medical.dao;

import com.medical.domain.Country;

import java.util.List;

public interface CountryDao extends GenericDao<Country> {

    public Country findByName(String countryName);
}
