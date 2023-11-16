package com.example.JournAI.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonCoords {

    @JsonProperty("lng")
    private String longatuide;

    @JsonProperty("lat")
    private String latitude;

    public String getLongatuide() {
        return longatuide;
    }

    public void setLongatuide(String longatuide) {
        this.longatuide = longatuide;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
