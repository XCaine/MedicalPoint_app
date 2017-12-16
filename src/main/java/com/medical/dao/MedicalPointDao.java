package com.medical.dao;

import com.medical.domain.MedicalPoint;
import com.medical.domain.MedicalUnit;
import com.medical.domain.MedicalUnitType;
import com.medical.domain.Specialty;

import java.util.List;
import java.util.Set;

public interface MedicalPointDao extends GenericDao<MedicalPoint> {

    public MedicalUnit FindMedicalUnitWithSpecialty(String specialtyName);
   // public void addMedicalUnit(String medicalUnitName, MedicalUnitType medicalUnitType, MedicalPoint medicalPoint, Specialty... specialties);

    public List<MedicalPoint> findWithIllnessAndCity(String illnessName, String cityName);

    public List<MedicalUnit> findAllMedicalUnits(MedicalPoint medicalPoint);

    public MedicalPoint findById(int id);

}
