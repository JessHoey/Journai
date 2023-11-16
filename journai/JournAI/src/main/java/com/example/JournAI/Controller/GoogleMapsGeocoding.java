package com.example.JournAI.Controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class GoogleMapsGeocoding {

    public static String googleMaps(String address) throws ApiException {
        try {
            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey("AIzaSyDXPPCaumqOsN8cOnn940Or0Y_WkGRzR54")
                    .build();
            GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
            context.shutdown();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.toJson(results); // Return the entire result
        } catch (Exception e) {
            // Handle exceptions here, e.g., log the error or throw a custom exception
            e.printStackTrace();
            return "{}"; // Return an empty JSON object to indicate an error
        }
    }
}



