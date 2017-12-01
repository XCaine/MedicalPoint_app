package com.medical.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "medical_unit_type", schema = "public", catalog = "medical_point")
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

    @NotNull
    @Length(min = 3, max = 60)
    @Column(name = "name", nullable = false, length = 60)
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_country", referencedColumnName = "id")
    private Country country;
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country countryByIdCountry) {
        this.country = countryByIdCountry;
    }
}
