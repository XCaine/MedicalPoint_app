package com.medical.service;

import com.medical.domain.MedicalPoint;
import com.medical.domain.MedicalUnit;

public interface EditMedicalPointService {
    public void updateAddress(MedicalPoint medicalPoint, String streetName, String streetNumber, String postalCode);
    public void updatePhoneNumber(MedicalPoint medicalPoint, String phoneNumber);
    public void addMedicalUnit(MedicalPoint medicalPoint, MedicalUnit medicalUnit);

}
