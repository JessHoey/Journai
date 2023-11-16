package com.example.JournAI.Controller;

import org.springframework.stereotype.Component;


@Component
public class FlipCoordinates {

    public String flipCoordinates(String coordinates) {
        char[] charArray = coordinates.toCharArray();

        StringBuilder sb = new StringBuilder();

        for (char letter : charArray) {
            if (!Character.isLetter(letter)){
                sb.append(letter);
            }
        }
        coordinates = sb.toString();
        String [] splitString = coordinates.split(",", 2);
        if(splitString.length == 2){
            String longitude = splitString[0];
            String latitude = splitString[1];

            coordinates = longitude.trim() + "," + latitude.trim();
        }
        return coordinates;
    }

}
