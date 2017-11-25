package com.medical.domain;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "country", schema = "public", catalog = "medicalpoint")
public class Country {

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
    @Column(name = "name", nullable = false, length = 40)
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "country")
    protected Set<Province> provinces = new HashSet<Province>();

    public Set<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(Set<Province> provinces) {
        this.provinces = provinces;
    }

    public void addProvinces(Province province){
        if(province == null)
            throw new NullPointerException("Can't add null province");
        if(province.getCountry()!= null)
            throw new IllegalStateException("Province is already assigned to a Country");

        getProvinces().add(province);
        province.setCountry(this);
    }

    @OneToMany(mappedBy = "country")
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



   /* public void readProvinces(){
        for(Province name : getProvinces()){
            System.out.println(name);
        }


    }
*/

}


//@Basic is ignored