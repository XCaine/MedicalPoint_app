package com.medical.json.deserializers;

import com.google.gson.*;
import com.medical.domain.Specialty;

import java.lang.reflect.Type;

public class SpecialtyDeserializer implements JsonDeserializer<Specialty>{
    @Override
    public Specialty deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Specialty specialty = new Specialty();
        String name = null;
        if(jsonObject.get("id")!= null)
            specialty.setId(jsonObject.get("id").getAsInt());
        if(jsonObject.get("name") != null)
            specialty.setName(name);
        return specialty;
    }
}
