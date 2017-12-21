package com.medical.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

import javax.persistence.Embeddable;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;

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
