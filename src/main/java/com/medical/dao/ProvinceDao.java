package com.medical.dao;

import com.medical.domain.City;
import com.medical.domain.Province;

public interface ProvinceDao extends GenericDao<Province>{

    public City findCityInProvinceByName(String cityName, Province province);
    public void addCity(String cityName, Province province);
}
