package com.medical.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "city", schema = "public", catalog = "medicalpoint")
public class City {

    public City(){}

    public City(Province province){
        this.province = province;
        province.getCities().add(this);
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


    @Column(name = "name")
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @ManyToOne
    @JoinColumn(name = "id_province", referencedColumnName = "id")
    public Province province;

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }


    @OneToMany(mappedBy = "city")
    protected Set<Address> addresses = new HashSet<Address>();

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public void addAddresses(Address address){
        if(address == null)
            throw new NullPointerException("Can't add null address");
        if(address.getCity()!= null)
            throw new IllegalStateException("City already assigned");
        getAddresses().add(address);
        address.setCity(this);
    }

}