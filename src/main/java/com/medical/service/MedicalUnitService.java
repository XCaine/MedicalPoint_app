package com.medical.service;

import com.medical.domain.MedicalUnit;
import com.medical.domain.MedicalUnitType;


public interface MedicalUnitService extends GenericService<MedicalUnit> {


    MedicalUnitType findMedicalUnitTypeById(int id);
}
