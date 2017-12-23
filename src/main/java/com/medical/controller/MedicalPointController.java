package com.medical.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.errors.ApiException;
import com.medical.domain.MedicalPoint;
import com.medical.objectmapper.*;
import com.medical.service.MedicalPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MedicalPointController {
    @Autowired
    private MedicalPointService medicalPointService;


    @RequestMapping("/medicalpoint/searchbyid/{id}")
    public ResponseEntity<String> get(@PathVariable("id") Integer id){
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MedicalPoint.class, new MedicalPointSerializer())
                .registerTypeAdapter(AddressSerializer.class, new AddressSerializer())
                .registerTypeAdapter(CoordinatesSerializer.class, new CoordinatesSerializer())
                .registerTypeAdapter(MedicalUnitSerializer.class, new MedicalUnitSerializer())
                .registerTypeAdapter(SpecialtySerializer.class, new SpecialtySerializer());
        gsonBuilder.setPrettyPrinting();

        final Gson gson = gsonBuilder.create();

        final String json = gson.toJson(medicalPointService.findMedicalPointById(id));
        return ResponseEntity.ok().body(json);
    }

    @RequestMapping("/medicalpoint/addbyname/{name}")
    public ResponseEntity<String> put(@PathVariable("name") String name)throws IOException, ApiException, InterruptedException{
        medicalPointService.addMedicalPointWithName(name);

        return ResponseEntity.ok().body(name + " succesfully added");
    }


}
