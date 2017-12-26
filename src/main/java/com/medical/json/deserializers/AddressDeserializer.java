package com.medical.json.deserializers;

import com.google.gson.*;
import com.medical.domain.Address;

import java.lang.reflect.Type;

public class AddressDeserializer implements JsonDeserializer<Address> {

    @Override
    public Address deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String streetName = null, streetNumber = null, postalCode = null;
        if(jsonObject.get("streetName")!= null)
        streetName = jsonObject.get("streetName").getAsString();
        if(jsonObject.get("streetNumber")!= null)
        streetNumber = jsonObject.get("streetNumber").getAsString();
        if(jsonObject.get("postalCode")!= null)
        postalCode = jsonObject.get("postalCode").getAsString();

        Address address = new Address();
        address.setStreetName(streetName);
        address.setStreetNumber(streetNumber);
        address.setPostalCode(postalCode);
        return address;
    }
}
