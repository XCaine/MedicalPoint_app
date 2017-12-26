package com.medical.json.deserializers;

import com.google.gson.*;
import com.medical.domain.MedicalPoint;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MedicalPointListDeserializer implements JsonDeserializer<List<MedicalPoint>> {
    @Override
    public List<MedicalPoint> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = new JsonObject();
        List<MedicalPoint> medicalPoints = new ArrayList<MedicalPoint>();
        JsonArray resultsJsonArray = jsonObject.getAsJsonArray("results");
        for(int i = 0; i < resultsJsonArray.size(); i++)
        {
            MedicalPoint medicalPoint = context.deserialize(resultsJsonArray.get(i), MedicalPoint.class);
            medicalPoints.add(medicalPoint);
        }
        return medicalPoints;
    }
}
