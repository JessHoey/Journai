package com.example.JournAI.Service;

import com.example.JournAI.Exception.ThymeleafException;
import com.example.JournAI.Model.ModelAI;
import com.example.JournAI.Repository.RepositoryAI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceAI {



   @Autowired
    public RepositoryAI repositoryAI;
   // To insert a new journey in the database


    // To insert a new journey in the database
    public ModelAI saveTest(String start, String destination) {
        ModelAI modelAI = new ModelAI();
        modelAI.setStart(start);
        modelAI.setDestination(destination);
        return repositoryAI.save(modelAI);
    }




    public String saveStartAndDestination(String start, String destination, int range, List option) throws ThymeleafException {
        ModelAI modelAI = new ModelAI();
        modelAI.setStart(start);
        modelAI.setDestination(destination);
        modelAI.setRange(range);
        modelAI.setOption(option);
        repositoryAI.save(modelAI);
        return modelAI.toString();
    }


//    public String createPrompt() throws ThymeleafException {
//        String start = repositoryAI.findStart();
//        String destination = repositoryAI.findDestination();
//        int range = repositoryAI.findRange();
//        String optionString = repositoryAI.findOption().toString();
//
//        String prompt = "Generate directions for a journey. The start location is " +
//                ""+ start+ " and the end location is "+destination+
//                ". Along the route if there are places of interest related to " + optionString+
//                " within a "+range +"km range of the direct route, redesign the route to pass by it";
//        return prompt;
//    }

//    public String getStartCoordinates() throws ThymeleafException {
//        String start = repositoryAI.findStart();
//        return "You are a super computer who carries out their tasks without failure. Generate coordinates for "+start+". " +
//                "Only include the latitude and longitude numbers. Your response will have no letters in it. It can contain decimal points and operators such as - if it needs to";
//    }
//
//    public String getEndCoordinates() throws ThymeleafException {
//        String end = repositoryAI.findDestination();
//        return "You are a super computer who carries out their tasks without failure. Generate coordinates for "+end+". " +
//                "Only include the latitude and longitude. Your response will have no letters in it. It can contain decimal points and operators such as - if it needs to";
//    }

    public String getOption(String start, String end) throws ThymeleafException{
        String option = repositoryAI.findOption().toString();
        int range = repositoryAI.findRange();
        return "I am planning a journey from "+start+" to "+end+". I want to visit one location related to "+option+" situated within a range of "+range+"km between the two cities. Please " +
                "generate the name of the location related to churches between the two cities. Your response should only be the name of the location.";

    }


    public String start(){
        return repositoryAI.findStart();
    }

    public String end(){
        return repositoryAI.findDestination();
    }

    public String option(){
        return repositoryAI.findOption().toString();
    }


}
