package com.medical.domain;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(name = "shifts", schema = "public", catalog = "medicalpoint")
public class Shifts {

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
    @Column(name = "start")
    private Timestamp start;
    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    @Basic
    @Column(name = "length")
    private int length;
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @OneToOne
    @JoinColumn(name = "id_medical_unit", referencedColumnName = "id")
    protected MedicalUnit medicalUnit;

    public MedicalUnit getMedicalUnit() {
        return medicalUnit;
    }

    public void setMedicalUnit(MedicalUnit medicalUnit) {
        this.medicalUnit = medicalUnit;
    }

   /* @ManyToOne
    @JoinColumn(name = "id_medical_unit", referencedColumnName = "id")
    public MedicalUnitEntity getMedicalUnitByIdMedicalUnit() {
        return medicalUnitByIdMedicalUnit;
    }

    public void setMedicalUnitByIdMedicalUnit(MedicalUnitEntity medicalUnitByIdMedicalUnit) {
        this.medicalUnitByIdMedicalUnit = medicalUnitByIdMedicalUnit;
    }*/
}
