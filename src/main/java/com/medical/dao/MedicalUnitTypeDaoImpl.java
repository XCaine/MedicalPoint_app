package com.medical.dao;


import com.medical.domain.MedicalPoint;
import com.medical.domain.MedicalUnitType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("medicalUnitTypeUnit")
public class MedicalUnitTypeDaoImpl extends AbstractGenericDao<MedicalUnitType> implements MedicalUnitTypeDao {

    @Autowired
    public MedicalUnitTypeDaoImpl(){super("MedicalUnitType");};
}
