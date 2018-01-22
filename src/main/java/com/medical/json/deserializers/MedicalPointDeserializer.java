package com.medical.json.deserializers;

import com.google.gson.*;
import com.medical.domain.*;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class MedicalPointDeserializer implements JsonDeserializer<MedicalPoint> {
    @Override
    public MedicalPoint deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context){
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        MedicalPoint medicalPoint = new MedicalPoint();
        String name = null, phoneNumber = null;
        if(jsonObject.get("id")!= null)
            medicalPoint.setId(jsonObject.get("id").getAsInt());
        if(jsonObject.get("name") != null)
            medicalPoint.setName(jsonObject.get("name").getAsString());
        if(jsonObject.get("phoneNumber") != null)
            medicalPoint.setPhoneNumber(jsonObject.get("phoneNumber").getAsString());
        if(jsonObject.get("address") != null) {
            Address address = context.deserialize(jsonObject.get("address"), Address.class);
            medicalPoint.setAddress(address);
        }
        if(jsonObject.get("coordinates") != null) {
            Coordinates coordinates = context.deserialize(jsonObject.get("coordinates"), Coordinates.class);
            medicalPoint.setCoordinates(coordinates);
        }
        if(jsonObject.get("city")!= null) {
            City city = new City();
            city.setName(jsonObject.get("city").getAsString());
            medicalPoint.setCity(city);

            if(jsonObject.get("province")!= null) {
                Province province = new Province();
                province.setName(jsonObject.get("province").getAsString());
                city.setProvince(province);

                if(jsonObject.get("country")!= null){
                    Country country = new Country();
                    country.setName(jsonObject.get("country").getAsString());
                    province.setCountry(country);
                }
            }
        }
        if(jsonObject.get("medicalUnits") != null){
            Set<MedicalUnit> medicalUnits = new HashSet<MedicalUnit>();
            JsonArray medicalUnitsJsonArray = jsonObject.getAsJsonArray("medicalUnits");
            for(int i = 0; i< medicalUnitsJsonArray.size(); i++) {
               MedicalUnit medicalUnit = context.deserialize(medicalUnitsJsonArray.get(i), MedicalUnit.class);
               medicalUnit.setMedicalPoint(medicalPoint);
            }
        }

        return medicalPoint;
    }
}
