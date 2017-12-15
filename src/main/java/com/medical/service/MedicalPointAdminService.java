package com.medical.service;

import com.medical.domain.MedicalPoint;
import com.medical.domain.MedicalUnit;
import com.medical.domain.MedicalUnitType;

import java.util.Set;

public interface MedicalPointAdminService {

    public Set<MedicalUnit> getMedicalUnits(MedicalPoint medicalPoint);

    //public void updateInfo(String name, String streetName, String streetNumber, String postalCode, String phoneNumber);

    public void addNewMedicalUnit(MedicalPoint medicalPoint, String medicalUnitName, MedicalUnitType medicalUnitType);


}
