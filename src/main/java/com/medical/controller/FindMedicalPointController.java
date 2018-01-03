package com.medical.controller;


import com.google.gson.*;
import com.medical.domain.MedicalPoint;
import com.medical.json.JsonModifier;
import com.medical.json.serializers.*;
import com.medical.service.FindMedicalPointService;
import com.medical.service.MedicalPointAdminService;
import com.medical.service.MedicalPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FindMedicalPointController {
    @Autowired
    private MedicalPointService medicalPointService;
    @Autowired
    private MedicalPointAdminService medicalPointAdminService;
    @Autowired
    private FindMedicalPointService findByIllness;


    @RequestMapping(value = "/medicalpoint/searchbyid/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> searchbyid(@PathVariable("id") Integer id){


        final Gson gson = JsonModifier.prepareJsonBuilderForMedicalPointSerializer();

        final String json = gson.toJson(medicalPointService.findMedicalPointById(id));
        return ResponseEntity.ok().body(JsonModifier.prepareResults(json));
    }


    @RequestMapping("/findmedicalpoint/findmedicalpoint")
    public ResponseEntity<String> findMedicalPointByIllness(@RequestParam ("lat")Double latitude,
                                                   @RequestParam("lon") Double longitude,
                                                   @RequestParam ("illness") String illness,
                                                   @RequestParam ("city") String city,
                                                   @RequestParam ("province") String province){


        final Gson gson = JsonModifier.prepareJsonBuilderForMedicalPointSerializer();

        String json = gson.toJson(findByIllness.getNearestMedicalPointByIllness(latitude, longitude, illness, city, province));

        return ResponseEntity.ok().body(JsonModifier.prepareResults(json));
    }


    @RequestMapping("/findmedicalpoint/findmedicalpoint2")
    public ResponseEntity<String> findMedicalPointBySpecialty(@RequestParam ("lat")Double latitude,
                                                   @RequestParam("lon") Double longitude,
                                                   @RequestParam ("specialty") String specialty,
                                                   @RequestParam ("city") String city,
                                                   @RequestParam ("province") String province){

        final Gson gson = JsonModifier.prepareJsonBuilderForMedicalPointSerializer();

        String json = gson.toJson(findByIllness.getNearestMedicalPointBySpeciality(latitude, longitude, specialty, city, province));

        return ResponseEntity.ok().body(JsonModifier.prepareResults(json));
    }

}
