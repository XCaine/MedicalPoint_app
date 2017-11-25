package com.medical.domain;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "business_hours", schema = "public", catalog = "medicalpoint")
public class BusinessHoursEntity {

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

    @Basic
    @Column(name = "open")
    private Time open;
    public Time getOpen() {
        return open;
    }

    public void setOpen(Time open) {
        this.open = open;
    }

    @Basic
    @Column(name = "close")
    private Time close;
    public Time getClose() {
        return close;
    }

    public void setClose(Time close) {
        this.close = close;
    }

    @Basic
    @Column(name = "day_of_week")
    private Short dayOfWeek;
    public Short getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Short dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Basic
    @Column(name = "last_update")
    private Timestamp lastUpdate;

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


    @OneToOne
    @JoinColumn(name = "id_medical_unit", referencedColumnName = "id")
    protected MedicalUnit medicalUnit;

    public MedicalUnit getMedicalUnit() {
        return medicalUnit;
    }

    public void setMedicalUnit(MedicalUnit medicalUnit) {
        this.medicalUnit = medicalUnit;
    }



}
