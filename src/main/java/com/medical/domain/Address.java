package com.medical.domain;

import jdk.nashorn.internal.ir.annotations.Immutable;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Embeddable
public class Address {


    @NotNull
    @Length(min = 2, max = 100)
    private String streetName;
    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @NotNull
    @Length(min = 1, max = 12)
    private String streetNumber;

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    @Length(min = 1, max = 12)
    private String postalCode;
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

/*
    public void addMedicalPoint(MedicalPoint medicalPoint){
        if(medicalPoint == null)
            throw new NullPointerException("Can't add null Medical Point");
        if(medicalPoint.getAddress()!=null)
            throw new IllegalStateException("Address already assigned");
        medicalPoint.setAddress(this);
    }*/

}
