package com.medical.service;

import com.medical.dao.MedicalPointDao;
import com.medical.dao.MedicalUnitDao;
import com.medical.domain.MedicalPoint;
import com.medical.domain.MedicalUnit;
import com.medical.domain.MedicalUnitType;
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

    public void addNewMedicalUnit(MedicalPoint medicalPoint, String medicalUnitName, MedicalUnitType medicalUnitType){
        Assert.notNull(medicalPoint, "MedicalPoint can't be null");
        Assert.notNull(medicalUnitName, "MedicalUnitName cant be null");
        Assert.notNull(medicalUnitType, "MedicalUnitTape can't be null");
        MedicalUnit medicalUnit= new MedicalUnit(medicalPoint, medicalUnitName, medicalUnitType);
        medicalUnitDao.save(medicalUnit);
    }

    public Set<MedicalUnit> getMedicalUnits(MedicalPoint medicalPoint){
        Assert.notNull(medicalPoint, "MedicalPoint can't be null");
        return medicalPointDao.findAllMedicalUnits(medicalPoint);
    }

}
