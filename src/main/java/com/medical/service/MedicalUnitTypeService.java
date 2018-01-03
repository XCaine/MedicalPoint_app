package com.medical.service;

import com.medical.domain.MedicalUnitType;

public interface MedicalUnitTypeService  extends GenericService<MedicalUnitType>{
    public MedicalUnitType findByName(String medicalUnitTypeName);
}
