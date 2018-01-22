package com.medical.service;

import com.medical.domain.Illness;
import com.medical.domain.MedicalPoint;

public interface FindMedicalPointService extends GenericService<Illness> {

    //public List<MedicalPoint> getMedicalPoints(String illnessName);

    MedicalPoint getNearestMedicalPointByIllness(double latitude, double longitude, String illnessName,
                                                 String cityName, String provinceName);

    MedicalPoint getNearestMedicalPointBySpecialty(double latitude, double longitude, String specialtyName,
                                                   String cityName, String provinceName);
}
