package com.medical.objectmapper;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.medical.domain.Address;
import com.medical.domain.Coordinates;
import com.medical.domain.MedicalPoint;
import com.medical.domain.MedicalUnit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Prototype Custom Gson TypeAdapter for parsing MedicalPoint object to JSON file and the other way round.
 */
public class MedicalPointAdapter extends TypeAdapter<MedicalPoint> {


    @Override
    public MedicalPoint read(final JsonReader in) throws IOException{
        final MedicalPoint medicalPoint = new MedicalPoint();


        in.beginObject();
        while(in.hasNext()){
            switch (in.nextName()){
                case "name": {
                    medicalPoint.setName(in.nextString());
                    break;
                }
                case "address": {
                    in.beginObject();
                    final Address address = new Address();
                    while(in.hasNext()) {
                        switch (in.nextName()) {
                            case "streetName": {
                                address.setStreetName(in.nextString());
                                break;
                            }
                            case "streetNumber":{
                                address.setStreetNumber(in.nextString());
                                break;
                            }
                            case "postalCode":{
                                address.setPostalCode(in.nextString());
                                break;
                            }
                            case "city":{
                                in.skipValue();
                                break;
                            }
                            case "province":{
                                in.skipValue();
                                break;
                            }
                        }
                    }
                    medicalPoint.setAddress(address);
                    in.endObject();
                    break;
                }
                case "coordinates":{
                    in.beginObject();
                    final Coordinates coordinates= new Coordinates();
                    while(in.hasNext()){
                        switch (in.nextName()){
                            case "latitude": {
                                coordinates.setY(in.nextDouble());
                                break;
                            }
                            case "longitude": {
                                coordinates.setX(in.nextDouble());
                                break;
                            }
                        }
                    }
                    medicalPoint.setCoordinates(coordinates);
                    in.endObject();
                    break;
                }
                case "phoneNumber": {
                    medicalPoint.setPhoneNumber(in.nextString());
                    break;
                }
                case "medicalUnits": {
                    in.beginArray();
                    final Set<MedicalUnit> medicalUnits= new HashSet<MedicalUnit>();
                    while(in.hasNext()){
                        in.beginObject();
                        final MedicalUnit mu = new MedicalUnit();
                        while (in.hasNext()) {
                            switch (in.nextName()){
                                case "id":
                                    mu.setId(in.nextInt());
                                    break;
                                case "name":
                                    mu.setName(in.nextString());
                                    break;
                                case "phoneNumber":
                                    mu.setPhoneNumber(in.nextString());
                                    break;

                            }
                        }
                        medicalUnits.add(mu);
                        in.endObject();
                    }
                    medicalPoint.setMedicalUnits(medicalUnits);
                    in.endArray();
                    break;
                }


            }

        }
        in.endObject();
        return medicalPoint;
    }

    @Override
    public void write(final JsonWriter out, final MedicalPoint medicalPoint) throws IOException{
        Set<MedicalUnit> medicalUnits = medicalPoint.getMedicalUnits();
        out.beginObject();
        out.name("name").value(medicalPoint.getName());
        out.name("phoneNumber").value(medicalPoint.getPhoneNumber());

        out.name("address");
        out.beginObject();
        out.name("streetName").value(medicalPoint.getAddress().getStreetName());
        out.name("streetNumber").value(medicalPoint.getAddress().getStreetNumber());
        out.name("postalCode").value(medicalPoint.getAddress().getPostalCode());
        medicalPoint.getCity().getId();

        out.name("city").value(medicalPoint.getCity().getName());
        out.name("province").value(medicalPoint.getCity().getProvince().getName());
        //out.name("country").value(medicalPoint.getCity().getProvince().getCountry().getName());
        out.endObject();

        out.name("coordinates");
        out.beginObject();
        out.name("longitude").value(medicalPoint.getCoordinates().getX());
        out.name("latitude").value(medicalPoint.getCoordinates().getY());
        out.endObject();

        out.name("medicalUnits");
        out.beginArray();
        for (MedicalUnit medunit : medicalUnits){
            out.beginObject();
            out.name("id").value(medunit.getId());
            out.name("name").value(medunit.getName());
            out.name("phoneNumber").value(medunit.getPhoneNumber());
            out.endObject();

        }
        out.endArray();


        out.endObject();
    }




}
