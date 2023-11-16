package com.example.JournAI.GoogleMapAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.xml.stream.Location;

@Data
public class Geometry {
    @JsonProperty("geometry")
    private String geometry;
}
