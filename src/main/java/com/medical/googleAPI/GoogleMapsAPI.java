package com.medical.googleAPI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import com.medical.domain.Address;
import com.medical.domain.City;
import com.medical.domain.Coordinates;
import com.medical.domain.MedicalPoint;

import java.io.IOException;

public class GoogleMapsAPI {
    // Google API Key used to send requests
    private String myApiKey = "AIzaSyBIDB0hfasjgD3hNrKtSz0X6EufWl820j0";
    public void CheckDirectionsBetween(String source, String destination, TravelMode travelMode) throws IOException, ApiException, InterruptedException {
        // API context used to send requests
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(myApiKey)
                .build();
        // make a request
        DirectionsApiRequest request = DirectionsApi.getDirections(context, source, destination);
        // set request settings
        request.units(Unit.METRIC);
        request.mode(travelMode);
        request.language("pl");
        // request.trafficModel(TrafficModel.BEST_GUESS);
        // show only 1 route (shortest one)
        request.alternatives(true);

        // wait for answer
        DirectionsResult directionsResults = request.await();

        String endLocation = directionsResults.routes[0].legs[0].endAddress;
        Duration duration = directionsResults.routes[0].legs[0].duration;
        Distance distance = directionsResults.routes[0].legs[0].distance;

        System.out.println("Podróż do " + endLocation + " zajmie " + duration + ". Długość trasy wynosi " + distance + ".");
    }
     public void geoCode(MedicalPoint medicalPoint) throws IOException, ApiException, InterruptedException {
        if(medicalPoint == null)
            throw new NullPointerException("Can't assign coordinates to null MP");
        if(medicalPoint.getCoordinates() != null)
            throw new IllegalStateException("Address already assigned to MP " + medicalPoint.getAddressLine());
        //np. Złota 9/12, 02-222 Warszawa, Mazowieckie Polska
        String addressString = medicalPoint.getAddressLine();
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(myApiKey)
                .build();
        GeocodingResult[] results = GeocodingApi.geocode(context, addressString).await();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Coordinates coordinates = new Coordinates();
        coordinates.setX(results[0].geometry.location.lat);
        coordinates.setY(results[0].geometry.location.lng);
        medicalPoint.setCoordinates(coordinates);
    }




    }


