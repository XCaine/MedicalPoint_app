package com.medical.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.medical.domain.MedicalPoint;
import com.medical.json.serializers.*;

public class JsonModifier {
    public static String prepareResults(String json){
        json = "{ \"results\":" + json;
        json = json.concat(" }");
        return json;
    }

    public static Gson prepareJsonBuilderForMedicalPointSerializer (){
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MedicalPoint.class, new MedicalPointSerializer())
                .registerTypeAdapter(AddressSerializer.class, new AddressSerializer())
                .registerTypeAdapter(CoordinatesSerializer.class, new CoordinatesSerializer())
                .registerTypeAdapter(MedicalUnitSerializer.class, new MedicalUnitSerializer())
                .registerTypeAdapter(SpecialtySerializer.class, new SpecialtySerializer());
        gsonBuilder.setPrettyPrinting();

        return gsonBuilder.create();
    }

}
