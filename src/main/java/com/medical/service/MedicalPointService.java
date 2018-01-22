package com.medical.service;

import com.google.maps.errors.ApiException;
import com.medical.domain.MedicalPoint;
import com.medical.domain.MedicalUnitType;
import com.medical.domain.Specialty;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface MedicalPointService {

    void add(MedicalPoint medicalPoint);


    void saveOrUpdate(MedicalPoint medicalPoint);



    void update(MedicalPoint medicalPoint);


    void remove(MedicalPoint medicalPoint);

    void removeAll();

    void removeByName(String medicalPointName);

    void removeById(Integer id);

    MedicalPoint findById(Integer id);

    List<MedicalPoint> findALL();

    MedicalPoint findByName(String medicalPointName);

    MedicalPoint addMedicalPointWithName(String name)throws IOException, ApiException, InterruptedException;

    void addMedicalUnit(String medicalUnitName, MedicalUnitType medicalUnitType, MedicalPoint medicalPoint, Set<Specialty> specialties);

    List<MedicalPoint> findWithIllnessAndCity(String illnessName, String cityName);

    MedicalPoint findMedicalPointById(int id);

    MedicalPoint findMedicalPointIncludingString(String medicalPointName);
}
