package com.medical.dao;

import com.medical.domain.MedicalPoint;

public interface MedicalPointDao extends GenericDao<MedicalPoint> {

    public MedicalPoint findByName(String medicalPointName);
}
