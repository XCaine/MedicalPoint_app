package com.medical.service;

import com.google.gson.JsonElement;
import com.medical.domain.MedicalPoint;
import com.medical.domain.MedicalUnit;

import java.util.List;

public interface MedicalPointAdminService {

    List  <MedicalUnit> getMedicalUnits(MedicalPoint medicalPoint);

    //public void updateInfo(String name, String streetName, String streetNumber, String postalCode, String phoneNumber);

    //public void addNewMedicalUnit(MedicalPoint medicalPoint, String medicalUnitName, MedicalUnitType medicalUnitType);

    void addMedicalPoint(JsonElement jsonElement);

    void addMedicalUnit(JsonElement jsonElement, int medicalPointId);

}
