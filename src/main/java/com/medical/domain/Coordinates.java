package com.medical.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Coordinates implements Serializable{
    private double latitude;
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double x) {
        this.latitude = x;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double y) {
        this.longitude = y;
    }

}
