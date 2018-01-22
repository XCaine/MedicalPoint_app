package com.medical.dao;

import com.medical.domain.MedicalPoint;
import com.medical.domain.MedicalUnit;
import com.medical.domain.MedicalUnitType;
import com.medical.domain.Specialty;

import java.util.List;

public interface MedicalPointDao extends GenericDao<MedicalPoint> {

    public MedicalUnit FindMedicalUnitWithSpecialty(String specialtyName);
   // public void addMedicalUnit(String medicalUnitName, MedicalUnitType medicalUnitType, MedicalPoint medicalPoint, Specialty... specialties);


    /**
     * Find medical points with given illness and city name
     * @param illnessName
     * @param cityName
     * @return List of medical points found with given illness name and city name
     */
    List<MedicalPoint> findWithIllnessAndCity(String illnessName, String cityName);

    /**
     * Find medical points with given illness and province name
     * @param illnessName
     * @param provinceName
     * @return List of medical points found with given illness name and province name
     */
    List<MedicalPoint> findWithIllnessAndProvince(String illnessName, String provinceName);

    /**
     * Find medical points with given specialty and city name
     * @param specialtyName
     * @param cityName
     * @return List of medical points found with given specialty name and city name
     */
    List<MedicalPoint> findWithSpecialtyAndCity(String specialtyName, String cityName);

    /**
     * Find medical points with given specialty and province name
     * @param specialtyName
     * @param provinceName
     * @return List of medical points found with given specialty name and province name
     */
    List<MedicalPoint> findWithSpecialtyAndProvince(String specialtyName, String provinceName);


    public List<MedicalUnit> findAllMedicalUnits(MedicalPoint medicalPoint);

    public MedicalPoint findById(int id);

    public MedicalPoint findClosestMatchByName(String medicalPointName);
}
