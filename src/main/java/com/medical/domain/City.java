package com.medical.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "city", schema = "public", catalog = "medical_point")
public class City {

    public City(){}

    public City(String cityName, Province province){
        setName(cityName);
        setProvince(province);
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
    @Length(min = 2, max=50)
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//!!!!!
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_province", referencedColumnName = "id")
    public Province province;

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }


    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    @Cascade({org.hibernate.annotations.CascadeType.DELETE})
    protected Set<MedicalPoint> medicalPoints = new HashSet<MedicalPoint>();

    public Set<MedicalPoint> getMedicalPoints() {
        return medicalPoints;
    }

    public void setMedicalPoints(Set<MedicalPoint> medicalPoints) {
        this.medicalPoints = medicalPoints;
    }

    public void addMedicalPoints(MedicalPoint medicalPoint){
        if(medicalPoint == null)
            throw new NullPointerException("Can't add null medical point");
        if(medicalPoint.getCity()!= null)
            throw new IllegalStateException("City already assigned to medical point");
        getMedicalPoints().add(medicalPoint);
        medicalPoint.setCity(this);
    }

}