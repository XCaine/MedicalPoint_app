package com.medical.service;

import com.medical.domain.Illness;
import com.medical.domain.MedicalPoint;

import java.util.List;

public interface IllnessService extends GenericService<Illness> {

    public List<MedicalPoint> getMedicalPoints(String illnessName);
}
