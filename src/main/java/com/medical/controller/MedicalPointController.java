package com.medical.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.maps.errors.ApiException;
import com.medical.domain.MedicalPoint;
import com.medical.json.serializers.*;
import com.medical.service.MedicalPointAdminService;
import com.medical.service.MedicalPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MedicalPointController {
    @Autowired
    private MedicalPointService medicalPointService;
    @Autowired
    private MedicalPointAdminService medicalPointAdminService;


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

    @RequestMapping("/medicalpoint/addmedicalpoint/{json}")
    public void add(@PathVariable("json")String string){
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(string).getAsJsonObject();
        medicalPointAdminService.addMedicalPoint(jsonElement);
    }

   // @RequestMapping("/")

}
