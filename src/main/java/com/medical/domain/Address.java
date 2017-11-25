package com.medical.domain;

import javax.persistence.*;

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

    @Basic
    @Column(name = "street_name")
    private String streetName;
    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @Basic
    @Column(name = "street_number")
    private String streetNumber;

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    @Basic
    @Column(name = "postal_code")
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



}
