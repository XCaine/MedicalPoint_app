package com.medical.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "specialty", schema = "public", catalog = "medical_point")
public class Specialty {


    public Specialty(){};

    public Specialty(String name){
        this.setName(name);
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
    @Length(min=3, max=50)
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Lob
    @Column(name = "description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany(cascade =  CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name = "specialty_illness", joinColumns = {@JoinColumn(name = "id_specialty")}, inverseJoinColumns = {@JoinColumn(name = "id_illness")})
    private Set<Illness> illnesses = new HashSet<Illness>();

    public Set<Illness> getIllnesses() {
        return illnesses;
    }

    public void setIllnesses(Set<Illness> illnesses) {
        this.illnesses = illnesses;
    }

    public void addIllnesses(Illness illness){
        if (illness == null)
            throw new NullPointerException("Can't add null illness");
        getIllnesses().add(illness);
    }

    @ManyToMany(mappedBy = "specialties", fetch=FetchType.EAGER)
    private Set<MedicalUnit> medicalUnits = new HashSet<MedicalUnit>();

    public Set<MedicalUnit> getMedicalUnits() {
        return medicalUnits;
    }

    public void setMedicalUnits(Set<MedicalUnit> medicalUnits) {
        this.medicalUnits = medicalUnits;
    }
}
