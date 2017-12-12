package com.medical.dao;

import com.medical.domain.MedicalPoint;
import com.medical.domain.MedicalUnit;
import com.medical.domain.MedicalUnitType;
import com.medical.domain.Specialty;

public interface MedicalPointDao extends GenericDao<MedicalPoint> {

    public MedicalUnit FindMedicalUnitWithSpecialty(String specialtyName);
   // public void addMedicalUnit(String medicalUnitName, MedicalUnitType medicalUnitType, MedicalPoint medicalPoint, Specialty... specialties);
}
