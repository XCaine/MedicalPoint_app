package com.medical.service;

import com.medical.domain.MedicalUnitType;

public interface MedicalUnitTypeService  extends GenericService<MedicalUnitType>{
    MedicalUnitType findByName(String medicalUnitTypeName);
}
