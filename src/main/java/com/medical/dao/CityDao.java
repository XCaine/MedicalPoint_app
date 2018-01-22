package com.medical.dao;

import com.medical.domain.City;

public interface CityDao extends  GenericDao<City>{

    City findByName(String cityName);
    //public void addNewCity(String cityName);
}
