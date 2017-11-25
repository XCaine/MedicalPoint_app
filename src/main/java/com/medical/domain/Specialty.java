package com.medical.domain;

import javax.persistence.*;
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

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(cascade =  CascadeType.ALL)
    @JoinTable(name = "specialty/illness", joinColumns = {@JoinColumn(name = "id_specialty")}, inverseJoinColumns = {@JoinColumn(name = "id_illness")})
    private Set<Illness> illnesses = new HashSet<Illness>();

}
