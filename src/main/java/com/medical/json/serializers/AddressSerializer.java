package com.medical.json.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.medical.domain.Address;

import java.lang.reflect.Type;

public class AddressSerializer implements JsonSerializer<Address> {

    @Override
    public JsonElement serialize(Address address, Type typeOfSource, JsonSerializationContext context){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("streetName", address.getStreetName());
        jsonObject.addProperty("streetNumber", address.getStreetNumber());
        jsonObject.addProperty("postalCode", address.getPostalCode());
        return jsonObject;
    }
}
