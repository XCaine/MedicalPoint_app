package com.medical.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "medical_point", schema = "public", catalog = "medicalpoint")
public class MedicalPoint {

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
    @Column(name = "phone_number")
    private String phoneNumber;
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    @OneToOne
    @JoinColumn(name = "id_address", referencedColumnName = "id")
    private MedicalPoint medicalPoint;

    public MedicalPoint getMedicalPoint() {
        return medicalPoint;
    }

    public void setMedicalPoint(MedicalPoint medicalPoint) {
        this.medicalPoint = medicalPoint;
    }



    @OneToMany(mappedBy = "medicalPoint")
    protected Set<MedicalUnit> medicalUnits= new HashSet<MedicalUnit>();

    public Set<MedicalUnit> getMedicalUnits() {
        return medicalUnits;
    }

    public void setMedicalUnits(Set<MedicalUnit> medicalUnits) {
        this.medicalUnits = medicalUnits;
    }
}
