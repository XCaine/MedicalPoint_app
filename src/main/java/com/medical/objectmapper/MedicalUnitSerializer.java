package com.medical.objectmapper;

import com.google.gson.*;
import com.medical.domain.MedicalUnit;
import com.medical.domain.Specialty;

import java.lang.reflect.Type;

public class MedicalUnitSerializer implements JsonSerializer<MedicalUnit>{

    @Override
    public JsonElement serialize(MedicalUnit medicalUnit, Type typeOfSource, JsonSerializationContext context){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", medicalUnit.getId());
        jsonObject.addProperty("name", medicalUnit.getName());
        jsonObject.addProperty("phoneNumber", medicalUnit.getPhoneNumber());
        jsonObject.addProperty("medicalUnitType", medicalUnit.getMedicalUnitType().getName());
        final JsonArray jsonSpecialtyArray = new JsonArray();
        for(final Specialty S : medicalUnit.getSpecialties())
        {
            final JsonElement specialty= context.serialize(S, SpecialtySerializer.class);
            jsonSpecialtyArray.add(specialty);
        }
        jsonObject.add("specialties", jsonSpecialtyArray);
        return jsonObject;
    }

}
