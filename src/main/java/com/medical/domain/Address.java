package com.medical.domain;

import jdk.nashorn.internal.ir.annotations.Immutable;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "address", schema = "public", catalog = "medicalpoint")
public class Address {


    @Id
    @Column(name = "id")
    private int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    @Length(min = 2, max = 60)
    @Column(name = "street_name", nullable = false, length = 60)
    private String streetName;
    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @NotNull
    @Length(min = 1, max = 10)
    @Column(name = "street_number", nullable = false, length = 10)
    private String streetNumber;

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    @Length(min = 1, max = 10)
    @Column(name = "postal_code", length = 10)
    private String postalCode;
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @ManyToOne
    @JoinColumn(name = "id_city", referencedColumnName = "id")
    protected City city;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void addMedicalPoint(MedicalPoint medicalPoint){
        if(medicalPoint == null)
            throw new NullPointerException("Can't add null Medical Point");
        if(medicalPoint.getAddress()!=null)
            throw new IllegalStateException("Address already assigned");
        medicalPoint.setAddress(this);
    }

}
