package com.medical.service;

import com.medical.dao.MedicalUnitTypeDao;
import com.medical.domain.MedicalUnitType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("medicalUnitTypeService")
@Transactional
public class MedicalUnitTypeServiceImpl extends GenericServiceImpl<MedicalUnitType> implements MedicalUnitTypeService{

    @Autowired
    MedicalUnitTypeDao medicalUnitTypeDao;

    @Override
    public MedicalUnitType findByName(String medicalUnitTypeName) {
        return medicalUnitTypeDao.findByName(medicalUnitTypeName);
    }
}
