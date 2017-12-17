package com.medical.service;


import com.medical.dao.MedicalUnitTypeDao;
import com.medical.domain.MedicalUnit;
import com.medical.domain.MedicalUnitType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("medicalUnitService")
@Transactional
public class MedicalUnitServiceImpl extends GenericServiceImpl<MedicalUnit> implements MedicalUnitService {

@Autowired
    MedicalUnitTypeDao medicalUnitTypeDao;

    public MedicalUnitType findMedicalUnitTypeById(int id)
    {
        return medicalUnitTypeDao.findById(id);
    }

}
