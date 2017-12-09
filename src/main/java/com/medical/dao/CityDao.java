package com.medical.dao;

import com.medical.domain.City;
import com.medical.domain.Country;

public interface CityDao extends  GenericDao<City>{

    public City findByName(String cityName);
    //public void addNewCity(String cityName);
}
