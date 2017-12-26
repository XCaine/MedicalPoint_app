package com.medical.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.medical.dao.MedicalPointDao;
import com.medical.dao.MedicalUnitDao;
import com.medical.dao.ProvinceDao;
import com.medical.domain.MedicalPoint;
import com.medical.domain.MedicalUnit;
import com.medical.domain.MedicalUnitType;
import com.medical.json.deserializers.MedicalPointDeserializer;
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

    public void addNewMedicalUnit(MedicalPoint medicalPoint, String medicalUnitName, MedicalUnitType medicalUnitType){
        Assert.notNull(medicalPoint, "MedicalPoint can't be null");
        Assert.notNull(medicalUnitName, "MedicalUnitName cant be null");
        Assert.notNull(medicalUnitType, "MedicalUnitTape can't be null");
        MedicalUnit medicalUnit= new MedicalUnit(medicalPoint, medicalUnitName, medicalUnitType);
        medicalUnitDao.save(medicalUnit);
    }

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
        medicalPointDao.save(medicalPoint);
    }

}
