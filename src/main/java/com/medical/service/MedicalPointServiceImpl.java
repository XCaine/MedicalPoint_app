package com.medical.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.GeocodingResult;
import com.medical.dao.CityDao;
import com.medical.dao.CountryDao;
import com.medical.dao.MedicalPointDao;
import com.medical.dao.ProvinceDao;
import com.medical.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.maps.model.AddressComponentType;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service("medicalPointService")
@Transactional
public class MedicalPointServiceImpl extends GenericServiceImpl<MedicalPoint> implements  MedicalPointService {

    @Autowired
    MedicalPointDao medicalPointDao;

    @Autowired
    CountryDao countryDao;

    @Autowired
    CityDao cityDao;

    @Autowired
    ProvinceDao provinceDao;

    @Override
    @Transactional(readOnly = false)
    public void add(MedicalPoint medicalPoint) {
        medicalPointDao.save(medicalPoint);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveOrUpdate(MedicalPoint medicalPoint) {
        medicalPointDao.saveOrUpdate(medicalPoint);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(MedicalPoint medicalPoint) {
        medicalPointDao.saveOrUpdate(medicalPoint);
    }

    @Override
    @Transactional(readOnly = false)
    public void remove(MedicalPoint medicalPoint) {
        medicalPointDao.remove(medicalPoint);
    }

    @Override
    public MedicalPoint findById(Integer id) {
        return medicalPointDao.findById(id);
    }

    @Override
    public List<MedicalPoint> findALL() {
        return medicalPointDao.findAll();
    }

    @Override
    public MedicalPoint findByName(String medicalPointName) {
        return medicalPointDao.findByName(medicalPointName);
    }

    @Override
    @Transactional(readOnly = false)
    public void removeByName(String medicalPointName) {
        MedicalPoint temp = medicalPointDao.findByName(medicalPointName);
        this.remove(temp);
    }

    @Override
    @Transactional(readOnly = false)
    public void removeById(Integer id) {
        MedicalPoint temp = medicalPointDao.findById(id);
        this.remove(temp);
    }

    @Override
    public void removeAll() {
        medicalPointDao.deleteAll();
    }

    private String myApiKey = "AIzaSyBIDB0hfasjgD3hNrKtSz0X6EufWl820j0";

    public void addMedicalPointWithName(String name) throws IOException, ApiException, InterruptedException {

        if (name == null)
            throw new NullPointerException("Name is null");
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(myApiKey)
                .build();
        GeocodingResult[] results = GeocodingApi.geocode(context, name).await();
        String countryName = null, provinceName = null, cityName = null, streetName = null, streetNumber = null, postalCode = null;

        for (AddressComponent ac : results[0].addressComponents) {
            for (AddressComponentType acType : ac.types) {
                System.out.println("iter");
                if (acType == AddressComponentType.COUNTRY) {
                    countryName = ac.longName;
                    //System.out.println("country");
                }
                if (acType == AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_1) {
                    provinceName = ac.longName.replace("województwo ", "");
                    //System.out.println("province");
                }
                if (acType == AddressComponentType.ADMINISTRATIVE_AREA_LEVEL_2) {
                    cityName = ac.longName;
                    //System.out.println(ac.longName);
                }
                if (acType == AddressComponentType.ROUTE) {
                    streetName = ac.shortName;
                    //System.out.println("st name");
                }
                if (acType == AddressComponentType.STREET_NUMBER) {
                    streetNumber = ac.longName;
                    //System.out.println("st nr");
                }
                if (acType == AddressComponentType.POSTAL_CODE) {
                    postalCode = ac.longName;
                    //System.out.println("postal code");
                }
            }
        }

        Country country = countryDao.findByName(countryName);
        if(country == null) throw new NullPointerException("Country is null");
        Province province = countryDao.findProvinceInCountryByName(provinceName, country);
        if(province == null) throw new NullPointerException("Province is null");
        City city = provinceDao.findCityInProvinceByName(cityName, province);
        if(city == null) {
            provinceDao.addCity(cityName, province);
            city = provinceDao.findCityInProvinceByName(cityName, province);
        }

        Address address = new Address();
        address.setPostalCode(postalCode);
        address.setStreetName(streetName);
        address.setStreetNumber(streetNumber);

        Coordinates coordinates = new Coordinates();
        coordinates.setX(results[0].geometry.location.lat);
        coordinates.setY(results[0].geometry.location.lng);

        MedicalPoint medicalPoint = new MedicalPoint();
        medicalPoint.setName(name);
        medicalPoint.setAddress(address);
        medicalPoint.setCoordinates(coordinates);
        medicalPoint.setCity(city);
        this.add(medicalPoint);
    }


    public void addMedicalUnit(String medicalUnitName, MedicalUnitType medicalUnitType, MedicalPoint medicalPoint, Set<Specialty> specialties){
        MedicalUnit medicalUnit = new MedicalUnit();
        medicalUnit.setName(medicalUnitName);
        medicalUnit.setMedicalUnitType(medicalUnitType);
        medicalUnit.setMedicalPoint(medicalPoint);
        medicalUnit.setSpecialties(specialties);
        this.saveOrUpdate(medicalPoint);
    }

    @Override
    public List<MedicalPoint> findWithIllnessAndCity(String illnessName, String cityName) {

        return medicalPointDao.findWithIllnessAndProvince(illnessName,cityName);
    }
}
