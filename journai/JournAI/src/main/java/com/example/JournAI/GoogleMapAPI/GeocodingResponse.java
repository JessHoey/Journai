package com.example.JournAI.GoogleMapAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import lombok.Data;

import java.io.IOException;
import java.util.List;

@Data
public class GeocodingResponse {
    private List<GeocodingResult> results;

//    public static GeocodingResponse parseJson(String jsonString) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.readValue(jsonString, GeocodingResponse.class);
//    }
//
//    public double getLatitude() {
//        GeocodingResult result = results.get(0);
//        Geometry geometry = result.getGeometry();
//        Location location = geometry.getLocation();
//        return location.getLat();
//    }
//
//    public double getLongitude() {
//        GeocodingResult result = results.get(0);
//        Geometry geometry = result.getGeometry();
//        Location location = geometry.getLocation();
//        return location.getLng();
//    }
//

}
