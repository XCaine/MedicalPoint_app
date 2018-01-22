package com.medical.service;

import com.medical.domain.MedicalPoint;
import com.medical.domain.MedicalUnit;

public interface EditMedicalPointService {
    void updateAddress(MedicalPoint medicalPoint, String streetName, String streetNumber, String postalCode);
    void updatePhoneNumber(MedicalPoint medicalPoint, String phoneNumber);
    void addMedicalUnit(MedicalPoint medicalPoint, MedicalUnit medicalUnit);

}
