package com.medical.domain;

import javax.persistence.*;

@Entity
@Table(name = "medical_unit_type", schema = "public", catalog = "medicalpoint")
public class MedicalUnitType{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @ManyToOne
    @JoinColumn(name = "id_country", referencedColumnName = "id")
    private Country country;
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country countryByIdCountry) {
        this.country = countryByIdCountry;
    }
}
