package com.medical.domain;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@DynamicUpdate
@Table(name = "medical_point", schema = "public", catalog = "medical_point")

@NamedEntityGraph(name = "medicalPoint.city.province.country",
attributeNodes = {  @NamedAttributeNode(value = "city", subgraph = "city")},
subgraphs = {
@NamedSubgraph(name = "city",
        attributeNodes = {@NamedAttributeNode(value = "province", subgraph = "province")}),
@NamedSubgraph(name = "province",
        attributeNodes = {@NamedAttributeNode(value = "country", subgraph = "country")})})


public class MedicalPoint {

   /* public MedicalPoint(){
        addressString = (getAddress().getStreetName() + " " + getAddress().getStreetNumber() + ", "+ getAddress().getPostalCode() + " " + getCity().getName() + ", " + getCity().getProvince().getName() + " " + getCity().getProvince().getCountry().getName());
    }*/

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


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "streetName",
                    column = @Column(name = "street_name", length = 100, nullable = false)),
            @AttributeOverride(name = "streetNumber",
                    column = @Column(name = "street_number", length = 12, nullable = false)),
            @AttributeOverride(name = "postalCode",
                    column = @Column(name = "postal_code", length = 12))
    })
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }



    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude",
                    column = @Column(name = "latitude")),
            @AttributeOverride(name = "longitude",
                    column = @Column(name = "longitude"))
    })
    private Coordinates coordinates;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
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
    @Length(min = 3, max = 200)
    @Column(name = "name", length = 200, nullable = false)
    private String name;
    public String getName() { return name; }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    @OneToMany(mappedBy = "medicalPoint", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
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


    @ManyToOne( fetch=FetchType.LAZY)// !!!
    @JoinColumn(name = "id_city", referencedColumnName = "id")
    private City city;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }





   public String getAddressLine() {
       return (getAddress().getStreetName() + " " + getAddress().getStreetNumber() + ", " + getAddress().getPostalCode());
   }

}
