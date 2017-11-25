package com.medical.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "medical_unit", schema = "public", catalog = "medicalpoint")
public class MedicalUnit {

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

    @Basic
    @Column(name = "phone_number")
    private String phoneNumber;
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @ManyToOne
    @JoinColumn(name = "id_medical_point", referencedColumnName = "id")
    protected MedicalPoint medicalPoint;

    public MedicalPoint getMedicalPoint() {
        return medicalPoint;
    }


    @OneToOne
    @JoinColumn(name = "id_medical_unit_type", referencedColumnName = "id")
    private MedicalUnitType medicalUnitType;

    public void setMedicalPoint(MedicalPoint medicalPoint) {
        this.medicalPoint = medicalPoint;
    }

    public void setMedicalUnitType(MedicalUnitType medicalUnitType) {
        this.medicalUnitType = medicalUnitType;
    }

    @ManyToMany
    @JoinTable(name = "medical_unit/specialty", joinColumns = {@JoinColumn(name = "id_medical_unit")}, inverseJoinColumns = {@JoinColumn(name = "id_specialty")})
    private Set<Specialty> specialties = new HashSet<Specialty>();

}
