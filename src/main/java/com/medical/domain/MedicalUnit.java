package com.medical.domain;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@DynamicUpdate
@Table(name = "medical_unit", schema = "public", catalog = "medical_point")

@NamedEntityGraph(name = "medicalPoint.medicalUnitType",
attributeNodes = {@NamedAttributeNode(value = "medicalUnitType", subgraph = "medicalUnitType")})
public class MedicalUnit {

    public MedicalUnit(){};

    public MedicalUnit(MedicalPoint medicalPoint, String medicalUnitName, MedicalUnitType medicalUnitType)
    {
        setMedicalPoint(medicalPoint);
        setName(medicalUnitName);
        setMedicalUnitType(medicalUnitType);
    }


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
    @Length(min=3, max=100)
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min=6, max=13)
    @Column(name = "phone_number", length = 13)
    private String phoneNumber;
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medical_point", referencedColumnName = "id")
    protected MedicalPoint medicalPoint;

    public MedicalPoint getMedicalPoint() {
        return medicalPoint;
    }

    public void setMedicalPoint(MedicalPoint medicalPoint) {
        this.medicalPoint = medicalPoint;
    }


    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_medical_unit_type", referencedColumnName = "id")
    private MedicalUnitType medicalUnitType;

    public void setMedicalUnitType(MedicalUnitType medicalUnitType) {
        this.medicalUnitType = medicalUnitType;
    }

    public MedicalUnitType getMedicalUnitType() {
        return medicalUnitType;
    }

    @ManyToMany(fetch=FetchType.EAGER)//FIX
    @JoinTable(name = "medical_unit_specialty", joinColumns = {@JoinColumn(name = "id_medical_unit")}, inverseJoinColumns = {@JoinColumn(name = "id_specialty")})
    private Set<Specialty> specialties = new HashSet<Specialty>();


    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
    }


    public void addShifts(Shifts shifts){
        if(shifts == null)
            throw new NullPointerException("Can't add null shifts");
        if(shifts.getMedicalUnit()!= null)
            throw new IllegalStateException("Medical Unit already assigned");
        shifts.setMedicalUnit(this);
    }

    public void addBusinessHours(BusinessHours businessHours){
        if(businessHours == null)
            throw new NullPointerException("Can't add null shifts");
        if(businessHours.getMedicalUnit()!= null)
            throw new IllegalStateException("Medical Unit already assigned");
        businessHours.setMedicalUnit(this);
    }

    public void addSpecialties(Specialty specialty){
        if(specialty == null)
            throw new NullPointerException("Can't add null specialty");
        getSpecialties().add(specialty);
    }

}
