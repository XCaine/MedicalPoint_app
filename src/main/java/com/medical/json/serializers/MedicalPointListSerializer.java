package com.medical.json.serializers;

import com.google.gson.*;
import com.medical.domain.MedicalPoint;

import java.lang.reflect.Type;
import java.util.List;

public class MedicalPointListSerializer implements JsonSerializer<List<MedicalPoint>> {

    @Override
    public JsonElement serialize(final List<MedicalPoint> medicalPointList, final Type typeOfSource, final JsonSerializationContext context){
        JsonObject jsonObject = new JsonObject();
        final JsonArray jsonMedicalPointArray = new JsonArray();
        for(MedicalPoint MP : medicalPointList) {
            final JsonElement jsonMedicalPoint = context.serialize(MP, MedicalPointSerializer.class);
            jsonMedicalPointArray.add(jsonMedicalPoint);
        }
        jsonObject.add("results", jsonMedicalPointArray);

        return jsonObject;
    }
}
