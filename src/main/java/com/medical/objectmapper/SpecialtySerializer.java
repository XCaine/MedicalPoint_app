package com.medical.objectmapper;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.medical.domain.Specialty;

import java.lang.reflect.Type;

public class SpecialtySerializer implements JsonSerializer<Specialty> {

    @Override
    public JsonElement serialize(Specialty specialty, Type typeOfSource, JsonSerializationContext context){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", specialty.getId());
        jsonObject.addProperty("name", specialty.getName());
        return jsonObject;
    }
}
