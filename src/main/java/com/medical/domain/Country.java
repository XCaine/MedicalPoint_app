package com.medical.domain;


import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Immutable;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Immutable
@Table(name = "country", schema = "public", catalog = "medical_point",
uniqueConstraints = @UniqueConstraint(columnNames ="name"))
public class Country {

    public Country(){}


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
    @Length(min=3, max=40)
    @Column(name = "name", nullable = false, length = 40, unique = true)
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    protected Set<Province> provinces = new HashSet<Province>();

    public Set<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(Set<Province> provinces) {
        this.provinces = provinces;
    }

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    @Cascade({org.hibernate.annotations.CascadeType.DELETE})
    protected  Set<MedicalUnitType> medicalUnitTypes = new HashSet<MedicalUnitType>();

    public Set<MedicalUnitType> getMedicalUnitTypes() {
        return medicalUnitTypes;
    }

    public void setMedicalUnitTypes(Set<MedicalUnitType> medicalUnitTypes) {
        this.medicalUnitTypes = medicalUnitTypes;
    }

    public void addMedicalUnitTypes(MedicalUnitType medicalUnitType){
        if(medicalUnitType == null)
            throw new NullPointerException("Can't add null medical unit type");
        if(medicalUnitType.getCountry()!= null)
            throw new IllegalStateException("Medical unit type is already assigned to a Country");

        getMedicalUnitTypes().add(medicalUnitType);
        medicalUnitType.setCountry(this);

    }

    public void addProvinces(Province province){
        if(province == null)
            throw new NullPointerException("Can't add null province");
        if(province.getCountry()!= null)
            throw new IllegalStateException("Province is already assigned to a Country");

        getProvinces().add(province);
        province.setCountry(this);

    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n*****Country INFO*****\n");
        sb.append("ID: " + getId() + "\n");
        sb.append("Name: " + getName() + "\n");
        sb.append("********************\n");
        return sb.toString();
    }
   /* public void readProvinces(){
        for(Province name : getProvinces()){
            System.out.println(name);
        }


    }
*/

}


//@Basic is ignored