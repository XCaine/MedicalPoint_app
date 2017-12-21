package com.medical.objectmapper;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.medical.domain.Coordinates;

import java.lang.reflect.Type;

public class CoordinatesSerializer implements JsonSerializer<Coordinates> {

    @Override
    public JsonElement serialize(Coordinates coordinates, Type typeOfSource, JsonSerializationContext context){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("latitude", coordinates.getLatitude());
        jsonObject.addProperty("longitude", coordinates.getLongitude());
        return jsonObject;
    }
}
