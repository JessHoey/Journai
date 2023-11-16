package com.example.JournAI.GoogleMapAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.maps.model.Geometry;
import lombok.Data;

@Data
public class GeocodingResult {
    @JsonProperty
    private Geometry geometry;

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
