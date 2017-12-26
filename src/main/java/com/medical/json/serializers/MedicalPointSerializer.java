package com.medical.json.serializers;

import com.google.gson.*;
import com.medical.domain.MedicalPoint;
import com.medical.domain.MedicalUnit;

import java.lang.reflect.Type;

public class MedicalPointSerializer implements JsonSerializer<MedicalPoint> {

    @Override
    public JsonElement serialize(final MedicalPoint medicalPoint, final Type typeOfSource, final JsonSerializationContext context){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", medicalPoint.getId());
        jsonObject.addProperty("name", medicalPoint.getName());
        jsonObject.addProperty("phoneNumber", medicalPoint.getPhoneNumber());
        jsonObject.addProperty("city", medicalPoint.getCity().getName());
        jsonObject.addProperty("province", medicalPoint.getCity().getProvince().getName());
        jsonObject.addProperty("country", medicalPoint.getCity().getProvince().getCountry().getName());
        final JsonElement jsonAddress = context.serialize(medicalPoint.getAddress(), AddressSerializer.class);
        jsonObject.add("address", jsonAddress);
        final JsonElement jsonCoordinates = context.serialize(medicalPoint.getCoordinates(), CoordinatesSerializer.class);
        jsonObject.add("coordinates", jsonCoordinates);
        final JsonArray jsonMedicalUnitsArray = new JsonArray();
        for (final MedicalUnit MU : medicalPoint.getMedicalUnits()){
            final JsonElement jsonMedicalUnit = context.serialize(MU, MedicalUnitSerializer.class);
            jsonMedicalUnitsArray.add(jsonMedicalUnit);
        }
        jsonObject.add("medicalUnits", jsonMedicalUnitsArray);

        return jsonObject;
    }

}
