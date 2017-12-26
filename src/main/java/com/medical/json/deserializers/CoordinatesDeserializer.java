package com.medical.json.deserializers;

import com.google.gson.*;
import com.medical.domain.Coordinates;

import java.lang.reflect.Type;

public class CoordinatesDeserializer implements JsonDeserializer<Coordinates>{
    @Override
    public Coordinates deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Double latitude, longitude;
        if(jsonObject.get("latitude")== null || jsonObject.get("longitude") == null) {
            return null;
        }

        latitude = jsonObject.get("latitude").getAsDouble();
        longitude = jsonObject.get("longitude").getAsDouble();

        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude(latitude);
        coordinates.setLongitude(longitude);
        return coordinates;
    }
}
