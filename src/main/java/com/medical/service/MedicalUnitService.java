package com.medical.service;

import com.medical.domain.Illness;
import com.medical.domain.MedicalUnit;
import com.medical.domain.MedicalUnitType;


public interface MedicalUnitService extends GenericService<MedicalUnit> {


    public MedicalUnitType findMedicalUnitTypeById(int id);
}
