package com.example.JournAI.Controller;

import com.mapbox.services.api.geocoding.v5.GeocodingCriteria;
import com.mapbox.services.api.geocoding.v5.MapboxGeocoding;
import com.mapbox.services.api.geocoding.v5.models.GeocodingResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class MapBox {

    @Value("${MAPBOX_ACCESS_TOKEN}")
    private String MAPBOX_ACCESS_TOKEN;


//    public String callMapBox() {
//        MapboxGeocoding mapboxGeocoding = MapboxGeocoding.builder()
//                .accessToken(MAPBOX_ACCESS_TOKEN)
//                .query("1600 Pennsylvania Ave NW")
//                .build();
//
//        return mapboxGeocoding.toString();
//    }


}







