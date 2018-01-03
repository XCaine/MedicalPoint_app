package com.medical.service;

import com.google.maps.errors.ApiException;
import com.medical.domain.MedicalPoint;
import com.medical.domain.MedicalUnitType;
import com.medical.domain.Specialty;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface MedicalPointService {

    public void add(MedicalPoint medicalPoint);


    public void saveOrUpdate(MedicalPoint medicalPoint);



    public void update(MedicalPoint medicalPoint);


    public void remove(MedicalPoint medicalPoint);

    public void removeAll();

    public void removeByName(String medicalPointName);

    public void removeById(Integer id);

    public MedicalPoint findById(Integer id);

    public List<MedicalPoint> findALL();

    public MedicalPoint findByName(String medicalPointName);

    public MedicalPoint addMedicalPointWithName(String name)throws IOException, ApiException, InterruptedException;

    public void addMedicalUnit(String medicalUnitName, MedicalUnitType medicalUnitType, MedicalPoint medicalPoint, Set<Specialty> specialties);

    public List<MedicalPoint> findWithIllnessAndCity(String illnessName, String cityName);

    public MedicalPoint findMedicalPointById(int id);
}
