package com.medical.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "province", schema = "public", catalog = "medical_point")
public class Province {

    public Province(){}

    public Province(Country country){
        this.country = country;
        country.getProvinces().add(this); //bidirectional (Country -> Province; Province -> Country)
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    public int getId() { return id; }

    public void setId(int id) { this.id = id; }


    @NotNull
    @Length(min =2, max=50)
    @Column(name = "name", nullable = false, length =50)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_country", referencedColumnName ="id")
    protected Country country;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @OneToMany(mappedBy = "province")
    protected Set<City> cities = new HashSet<City>();

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    public void addCities(City city){
        if(city == null)
            throw new NullPointerException("Can't add null city");
        if(city.getProvince() != null)
            throw new IllegalStateException("Province already assigned");
        getCities().add(city);
        city.setProvince(this);

    }



}


