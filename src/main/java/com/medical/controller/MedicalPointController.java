package com.medical.controller;


import com.google.gson.*;
import com.google.maps.errors.ApiException;
import com.medical.domain.MedicalPoint;
import com.medical.json.serializers.*;
import com.medical.service.FindByIllness;
import com.medical.service.MedicalPointAdminService;
import com.medical.service.MedicalPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class MedicalPointController {
    @Autowired
    private MedicalPointService medicalPointService;
    @Autowired
    private MedicalPointAdminService medicalPointAdminService;
    @Autowired
    private FindByIllness findByIllness;


    @RequestMapping(value = "/medicalpoint/searchbyid/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> searchbyid(@PathVariable("id") Integer id){
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

    @RequestMapping(value = "/medicalpoint/addwithname/{name}")
    public ResponseEntity<String> addByName(@PathVariable String name)throws IOException, ApiException, InterruptedException{
        medicalPointService.addMedicalPointWithName(name);

        return ResponseEntity.ok().body(name + " successfully added");
    }

    @RequestMapping("/medicalpoint/addwithJSON")
    public ResponseEntity<String> addMedicalPoint(@RequestParam ("json")String string){
        //string = "{ \"name\": \"TESTIN\", \"city\": \"sochaczewski\", \"province\": \"mazowieckie\", \"country\": \"Poland\", \"address\": { \"streetName\": \"Marsz. Piłsudskiego\", \"streetNumber\": \"65\", \"postalCode\": \"96-500\" }, \"coordinates\": { \"latitude\": 52.2388448, \"longitude\": 20.2277301 }, \"medicalUnits\": [ { \"id\": 5, \"name\": \"Oddział Okulistyki\", \"phoneNumber\": \"700400298\", \"specialties\": [ { \"id\": 23, \"name\": \"Ophthalmology\" } ] } ] }";
        System.out.println(string);
        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(string, JsonElement.class);
        medicalPointAdminService.addMedicalPoint(jsonElement);

        return ResponseEntity.ok().body("Medical point successfully added");
    }

    @RequestMapping("/medicalpoint/findmedicalpoint")
    public ResponseEntity<String> findMedicalPoint(@RequestParam ("lat")Double latitude,
                                                   @RequestParam("lon") Double longitude,
                                                   @RequestParam ("illness") String illness,
                                                   @RequestParam ("city") String city,
                                                   @RequestParam ("province") String province){
        findByIllness.getNearestMedicalPoint(latitude, longitude, illness, city, province);
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MedicalPoint.class, new MedicalPointSerializer())
                .registerTypeAdapter(AddressSerializer.class, new AddressSerializer())
                .registerTypeAdapter(CoordinatesSerializer.class, new CoordinatesSerializer())
                .registerTypeAdapter(MedicalUnitSerializer.class, new MedicalUnitSerializer())
                .registerTypeAdapter(SpecialtySerializer.class, new SpecialtySerializer());
        gsonBuilder.setPrettyPrinting();

        final Gson gson = gsonBuilder.create();

        final String json = gson.toJson(findByIllness.getNearestMedicalPoint(latitude, longitude, illness, city, province));
        return ResponseEntity.ok().body(json);
    }

   // @RequestMapping("/")

}
