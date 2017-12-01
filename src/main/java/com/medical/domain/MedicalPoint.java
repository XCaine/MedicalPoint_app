package com.medical.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "medical_point", schema = "public", catalog = "medical_point")
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

    @Length(min = 6, max=13)
    @Column(name = "phone_number", length = 13)
    private String phoneNumber;
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @NotNull
    @Length(min = 3, max = 150)
    @Column(name = "name", length = 150, nullable = false)
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @OneToOne
    @JoinColumn(name = "id_address", referencedColumnName = "id")
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    @OneToMany(mappedBy = "medicalPoint")
    protected Set<MedicalUnit> medicalUnits= new HashSet<MedicalUnit>();

    public Set<MedicalUnit> getMedicalUnits() {
        return medicalUnits;
    }

    public void setMedicalUnits(Set<MedicalUnit> medicalUnits) {
        this.medicalUnits = medicalUnits;
    }

    public void addMedicalUnits(MedicalUnit medicalUnit){
        if(medicalUnit == null)
            throw new NullPointerException("Can't add null Medical Unit");
        if(medicalUnit.getMedicalPoint()!= null)
            throw new IllegalStateException("Medical Point already assigned");
        getMedicalUnits().add(medicalUnit);
        medicalUnit.setMedicalPoint(this);
    }


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column(name = "longitude")),
            @AttributeOverride(name = "y", column = @Column(name = "latitude"))
    })
    private Coordinates coordinates;
}
