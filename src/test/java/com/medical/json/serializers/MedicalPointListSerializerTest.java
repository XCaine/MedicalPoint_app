package com.medical.json.serializers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.medical.config.AppConfig;
import com.medical.domain.Address;
import com.medical.domain.MedicalPoint;
import com.medical.json.deserializers.AddressDeserializer;
import com.medical.json.deserializers.MedicalPointDeserializer;
import com.medical.service.FindByIllness;
import com.medical.service.MedicalPointService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
class MedicalPointListSerializerTest {



   /* @Test
    void serialize() {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MedicalPointService medService = (MedicalPointService) context.getBean("medicalPointService");

        FindByIllness findByIllness = (FindByIllness) context.getBean("findByIllnessService");
        Type listOfMedicalPoints = new TypeToken<List<MedicalPoint>>(){}.getType();
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(listOfMedicalPoints, new MedicalPointListSerializer())
                .registerTypeAdapter(MedicalPointSerializer.class, new MedicalPointSerializer())
                .registerTypeAdapter(CoordinatesSerializer.class, new CoordinatesSerializer())
                .registerTypeAdapter(AddressSerializer.class, new AddressSerializer())
                .registerTypeAdapter(MedicalUnitSerializer.class, new MedicalUnitSerializer())
                .registerTypeAdapter(SpecialtySerializer.class, new SpecialtySerializer());

        gsonBuilder.setPrettyPrinting();

        final Gson gson = gsonBuilder.create();

        final String json = gson.toJson(medService.findALL(), listOfMedicalPoints);
        System.out.println(json);



        GsonBuilder gsonBuilder1 = new GsonBuilder();
        gsonBuilder1.registerTypeAdapter(MedicalPoint.class, new MedicalPointDeserializer())
                .registerTypeAdapter(AddressDeserializer.class, new AddressDeserializer());
        Gson gson1 = gsonBuilder1.create();

        MedicalPoint medicalPoint = gson1.fromJson(json, MedicalPoint.class);
        System.out.println(medicalPoint.getAddressLine());

    }*/

    @Test
    public void serialize2(){
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


        GsonBuilder gsonBuilder1 = new GsonBuilder();
        gsonBuilder1.registerTypeAdapter(MedicalPoint.class, new MedicalPointDeserializer());
        Gson gson1 = gsonBuilder1.create();

        MedicalPoint medicalPoint = gson1.fromJson(json, MedicalPoint.class);
        System.out.println(medicalPoint.getAddressLine() + medicalPoint.getCoordinates().getLatitude() + medicalPoint.getCoordinates().getLongitude() + medicalPoint.getCity().getName());


    }

   /* @Test
    public void testgsonTypeAdapter(){
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MedicalPointService medService = (MedicalPointService) context.getBean("medicalPointService");

        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MedicalPoint.class, new MedicalPointAdapter());
        gsonBuilder.setPrettyPrinting();

        final Gson gson = gsonBuilder.create();


        final String json = gson.toJson(medService.findMedicalPointById(20));
        System.out.println(json);

        final MedicalPoint mp = gson.fromJson(json, MedicalPoint.class);
        System.out.println(mp.getName());
    }*/
}