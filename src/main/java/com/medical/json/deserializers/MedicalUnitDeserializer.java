package com.medical.json.deserializers;

import com.google.gson.*;
import com.medical.domain.MedicalUnit;
import com.medical.domain.MedicalUnitType;
import com.medical.domain.Specialty;
import com.medical.service.MedicalUnitService;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class MedicalUnitDeserializer implements JsonDeserializer<MedicalUnit> {

    @Override
    public MedicalUnit deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = jsonElement.getAsJsonObject();
        MedicalUnit medicalUnit = new MedicalUnit();
        if(jsonObject.get("id") != null)
            medicalUnit.setId(jsonObject.get("id").getAsInt());
        if(jsonObject.get("name") != null)
            medicalUnit.setName(jsonObject.get("name").getAsString());
        if(jsonObject.get("phoneNumber") != null)
            medicalUnit.setPhoneNumber(jsonObject.get("phoneNumber").getAsString());
        if(jsonObject.get("specialties") != null){
            Set<Specialty> specialties = new HashSet<Specialty>();
            JsonArray specialtiesJsonArray = jsonObject.getAsJsonArray("specialties");
            for(int i = 0; i<specialtiesJsonArray.size(); i++){
                Specialty specialty = context.deserialize(specialtiesJsonArray.get(i), Specialty.class);
                specialties.add(specialty);
            }
            medicalUnit.setSpecialties(specialties);
        }

        return medicalUnit;
    }
}
