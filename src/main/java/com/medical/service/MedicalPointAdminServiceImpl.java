package com.medical.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.medical.dao.MedicalPointDao;
import com.medical.dao.MedicalUnitDao;
import com.medical.dao.MedicalUnitTypeDao;
import com.medical.dao.ProvinceDao;
import com.medical.domain.MedicalPoint;
import com.medical.domain.MedicalUnit;
import com.medical.domain.MedicalUnitType;
import com.medical.json.deserializers.MedicalPointDeserializer;
import com.medical.json.deserializers.MedicalUnitDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Set;

@Service("medicalPointAdminService")
@Transactional
public class MedicalPointAdminServiceImpl implements MedicalPointAdminService {

    @Autowired
    MedicalUnitDao medicalUnitDao;
    @Autowired
    MedicalPointDao medicalPointDao;
    @Autowired
    ProvinceDao provinceDao;
    @Autowired
    MedicalUnitTypeDao medicalUnitTypeDao;


    public List<MedicalUnit> getMedicalUnits(MedicalPoint medicalPoint){
        Assert.notNull(medicalPoint, "MedicalPoint can't be null");
        return medicalPointDao.findAllMedicalUnits(medicalPoint);
    }

    public void addMedicalPoint(JsonElement jsonElement){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MedicalPoint.class, new MedicalPointDeserializer());
        Gson gson = gsonBuilder.create();
        MedicalPoint medicalPoint = gson.fromJson(jsonElement, MedicalPoint.class);
        medicalPoint.setCity(provinceDao.findCityInProvince(medicalPoint.getCity()));
        if(medicalPoint.getCity() != null)
            medicalPointDao.save(medicalPoint);
        else
            throw new NullPointerException("City doesn't exist");
    }

    public void addMedicalUnit(JsonElement jsonElement, int medicalPointId){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MedicalPoint.class, new MedicalUnitDeserializer());
        Gson gson = gsonBuilder.create();
        MedicalUnit medicalUnit = gson.fromJson(jsonElement, MedicalUnit.class);
        medicalUnit.setMedicalUnitType(medicalUnitTypeDao.findByName(medicalUnit.getMedicalUnitType().getName()));
        medicalUnit.setMedicalPoint(medicalPointDao.findById(medicalPointId));
        medicalUnitDao.save(medicalUnit);
    }

}
