package com.medical.objectmapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.medical.config.AppConfig;
import com.medical.domain.Address;
import com.medical.domain.Coordinates;
import com.medical.domain.MedicalPoint;
import com.medical.domain.Specialty;
import com.medical.service.MedicalPointService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.FileWriter;
import java.io.IOException;

@Controller

public class GsonTest {

/*

    @Test
    public void testgsonTypeAdapter() throws IOException{


        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MedicalPoint.class, new MedicalPointAdapter());
        gsonBuilder.setPrettyPrinting();

        final Gson gson = gsonBuilder.create();


        final String json = gson.toJson(medService.findMedicalPointById(20));
        System.out.println(json);

        final MedicalPoint mp = gson.fromJson(json, MedicalPoint.class);
        System.out.println(mp.getName());


    }
*/
    @Test
    public void testgson2Serializer() throws IOException{

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MedicalPointService medService = (MedicalPointService) context.getBean("medicalPointService");
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MedicalPoint.class, new MedicalPointSerializer())
        .registerTypeAdapter(CoordinatesSerializer.class, new CoordinatesSerializer())
        .registerTypeAdapter(AddressSerializer.class, new AddressSerializer())
        .registerTypeAdapter(MedicalUnitSerializer.class, new MedicalUnitSerializer())
        .registerTypeAdapter(SpecialtySerializer.class, new SpecialtySerializer());
        
        gsonBuilder.setPrettyPrinting();

        final Gson gson = gsonBuilder.create();

        final String json = gson.toJson(medService.findMedicalPointById(20));
        System.out.println(json);

    }


}
