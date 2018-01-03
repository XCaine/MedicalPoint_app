package com.medical.service;

import com.medical.domain.Illness;
import com.medical.domain.MedicalPoint;

import java.util.List;

public interface FindMedicalPointService extends GenericService<Illness> {

    //public List<MedicalPoint> getMedicalPoints(String illnessName);

    public MedicalPoint getNearestMedicalPointByIllness(double latitude, double longitude, String illnessName,
                                                        String cityName, String provinceName);

    public MedicalPoint getNearestMedicalPointBySpeciality(double latitude, double longitude, String specialtyName,
                                                        String cityName, String provinceName);
}
