package com.medical.service;

import com.google.maps.errors.ApiException;
import com.medical.domain.MedicalPoint;

import java.io.IOException;
import java.util.List;

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

    public void addMedicalPointWithName(String name)throws IOException, ApiException, InterruptedException;
}
