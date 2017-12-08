package com.medical.domain;

import jdk.nashorn.internal.ir.annotations.Immutable;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Embeddable
public class Address {

    @Column(length = 100, nullable = false)
    @NotNull
    @Length(min = 2, max = 100)
    private String streetName;
    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @Column(length = 12, nullable = false)
    @NotNull
    @Length(min = 1, max = 12)
    private String streetNumber;

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    @Column(length = 12)
    @Length(min = 1, max = 12)
    private String postalCode;
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }


}
