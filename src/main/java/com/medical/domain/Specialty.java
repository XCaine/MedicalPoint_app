package com.medical.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "specialty", schema = "public", catalog = "medicalpoint")
public class Specialty {
    private int id;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    @Length(min=3, max=50)
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @ManyToMany(cascade =  CascadeType.ALL)
    @JoinTable(name = "specialty/illness", joinColumns = {@JoinColumn(name = "id_specialty")}, inverseJoinColumns = {@JoinColumn(name = "id_illness")})
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

}
