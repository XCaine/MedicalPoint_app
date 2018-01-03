package com.medical.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.maps.errors.ApiException;
import com.medical.service.MedicalPointAdminService;
import com.medical.service.MedicalPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AdminController {

    @Autowired
    private MedicalPointService medicalPointService;
    @Autowired
    private MedicalPointAdminService medicalPointAdminService;


    @RequestMapping(value = "/admin/medicalpoint/addwithname/{name}")
    public ResponseEntity<String> addByName(@PathVariable String name)throws IOException, ApiException, InterruptedException{
        medicalPointService.addMedicalPointWithName(name);

        return ResponseEntity.ok().body(name + " successfully added");
    }


    @RequestMapping("/admin/medicalpoint/addwithJSON")
    public ResponseEntity<String> addMedicalPoint(@RequestParam("json")String medicalPointJson){
        //string = "{ \"name\": \"TESTIN\", \"city\": \"sochaczewski\", \"province\": \"mazowieckie\", \"country\": \"Poland\", \"address\": { \"streetName\": \"Marsz. Piłsudskiego\", \"streetNumber\": \"65\", \"postalCode\": \"96-500\" }, \"coordinates\": { \"latitude\": 52.2388448, \"longitude\": 20.2277301 }, \"medicalUnits\": [ { \"id\": 5, \"name\": \"Oddział Okulistyki\", \"phoneNumber\": \"700400298\", \"specialties\": [ { \"id\": 23, \"name\": \"Ophthalmology\" } ] } ] }";
        System.out.println(medicalPointJson);
        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(medicalPointJson, JsonElement.class);
        medicalPointAdminService.addMedicalPoint(jsonElement);

        return ResponseEntity.ok().body("Medical point successfully added");
    }

    @RequestMapping("/admin/medicalpoint/addmedicalunit")
    public ResponseEntity<String> addMedicalUnit (@RequestParam ("medicalPointId") Integer medicalPointId,
                                                  @RequestParam ("json")String medicalUnitJson){
        Gson gson= new Gson();
        JsonElement jsonElement = gson.fromJson(medicalUnitJson, JsonElement.class);
        medicalPointAdminService.addMedicalUnit(jsonElement, medicalPointId);

        return ResponseEntity.ok().body("Medical Unit successfully added");
    }
}
