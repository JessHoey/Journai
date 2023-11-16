package com.example.JournAI.Controller;


import com.example.JournAI.Exception.ThymeleafException;
import com.example.JournAI.Model.JsonCoords;
import com.example.JournAI.Model.Locations;
import com.example.JournAI.Model.ModelAI;
import com.example.JournAI.Model.SideBox;
import com.example.JournAI.Repository.RepositoryAI;
import com.example.JournAI.Service.ServiceAI;
import com.example.JournAI.Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.maps.errors.ApiException;
import com.theokanning.openai.completion.CompletionRequest;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Controller
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private RepositoryAI repositoryAI;

    @Autowired
    private ServiceAI serviceAI;
    // API key for OpenAI

    @Autowired
    private FlipCoordinates flipCoordinates;

    @Autowired
    private GPT gpt;

    @Value("${apiKey")
    private String apiKey;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    //SignUp Page
    @GetMapping("/registerPage")
    public String getRegister() {
        return "register_page";
    }


    @GetMapping("/loginPage")
    public String getLoginPage() {

        return "login_page";
    }

    @GetMapping("/updatePage")
    public String getUpdatePage() {

        return "update_page";
    }
    @GetMapping("/main")
    public String getMain() {
        return "main";
    }

    //SignIn Page

    @GetMapping("/ForgetPage")
    public String getForgetPage() {

        return "forgetPassword_Page";
    }


    @GetMapping("/ErrorPage")
    public String getErrorPage() {

        return "Error_page";
    }

    @PostMapping("/register")
    public String getRegisterPage(@RequestParam String username, @RequestParam String email, @RequestParam String user_password, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("email", email);
        model.addAttribute("user_password", user_password);

        int minLength = 8;
        int maxLength = 15;

        if(isPasswordValid(user_password, minLength, maxLength)){

            boolean results = userService.registerUser(username, email, user_password);
            if (results) {
//                String tokenError = "Username is existing \n Pleas Use Another Username.";
//                model.addAttribute("tokenError", tokenError);
                return "redirect:/loginPage";
            } else {
                model.addAttribute("errorMessage", "Your Email Address already existing.. Please try again.");
                return "Error_page";
            }

        }
        else{
            model.addAttribute("errorMessage", "Password is invalid. It must be between " + minLength + " and " + maxLength + " characters long.");
            return "register_page";
        }



    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String user_password, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("user_password", user_password);


        String user = userService.verifyUser(username, user_password);
        if (user == "verify") {
            return "redirect:/main";
        } else {
            if (user == "userPassword") {
                model.addAttribute("errorMessage", "Your Password is wrong!.. Please try again.");
                return "login_page";
            }

            model.addAttribute("errorMessage", "Your Username is wrong!.. Please try again.");
            return "login_page";

        }

    }
    @PostMapping("/forget")
    public String forget(@RequestParam String email, Model model) {
        model.addAttribute("email", email);

        boolean result = userService.forgetPassWord(email);
        if (result) {
            return "redirect:/main";
        } else {
            model.addAttribute("errorMessage", "Your Email is not exist!.. Please try again.");
            return "forgetPassword_Page";


        }
    }

    @PostMapping("/updatePassword")
    public String updatePasswordPage(@RequestParam String username, @RequestParam String newPassword, @RequestParam String confirmPassword, Model model) {


        model.addAttribute("username", username);
        model.addAttribute("newPassword", newPassword);
        model.addAttribute("confirmPassword", confirmPassword);

        int minLength = 8;
        int maxLength = 15;

        if(isPasswordValid(confirmPassword, minLength, maxLength)){
            String result = userService.updatePassword(username, newPassword, confirmPassword);

            if (result == "save") {
                return "redirect:/loginPage";
            }
            else {

                if (result == "user") {
                    model.addAttribute("errorMessage", "Your Email is wrong!.. Please try again.");
                    return "update_page";
                }
                model.addAttribute("errorMessage", "Please Check your password");
                return "update_page";
            }
        }
        else{
            model.addAttribute("errorMessage", "Password is invalid. It must be between " + minLength + " and " + maxLength + " characters long.");
            return "update_page";
        }


    }
    public static boolean isPasswordValid(String password, int minLength, int maxLength) {
        int passwordLength = password.length();
        return passwordLength >= minLength && passwordLength <= maxLength;
    }
    //Save Method
    @PostMapping("/save")  // Save information from the form to the database
    public String saveJourneyParameters(@RequestParam String start,
                                        @RequestParam String destination, Model model,
                                        @RequestParam int range,
                                        @RequestParam(required = false, defaultValue = "") String option1,
                                        @RequestParam(required = false, defaultValue = "") String option2,
                                        @RequestParam(required = false, defaultValue = "") String option3) throws ThymeleafException, JsonProcessingException, ApiException, InterruptedException {

        model.addAttribute("start", start);
        model.addAttribute("destination", destination);
        model.addAttribute("range", range);

        // Temporary list to hold the options
        List<String> tempList = new ArrayList<String>();
        tempList.add(option1);
        tempList.add(option2);
        tempList.add(option3);

        // Actual list that will be saved to the database.
        List<String> option = new ArrayList<String>();

        //If the option is not null it will be added to the database
        for (int i = 0; i < tempList.size(); i++) {
            if (!Objects.equals(tempList.get(i), "")) {
                option.add(tempList.get(i));
            }
        }
        model.addAttribute("option", option);
        serviceAI.saveStartAndDestination(start, destination, range, option);


        Locations locations = generateMap();
        model.addAttribute("locations", locations);
        return "Map";
    }

    public Locations generateMap() throws JsonProcessingException, ApiException, ThymeleafException {
        String startCoords = generateStartCoordinates();
        String optionCoords = generateOptionCoordinates();
        String endCoords = generateEndCoordinates();

        Locations locations = new Locations(startCoords, optionCoords, endCoords);

        return locations;
    }


    @GetMapping("/start")
    public String generateStartCoordinates() throws ApiException, JsonProcessingException {
        String start = serviceAI.start();
        String jsonData = GoogleMapsGeocoding.googleMaps(start);

        ObjectMapper mapper =  new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonData);

        String lat = jsonNode.get(0).get("geometry").get("location").get("lat").asText();
        String lng = jsonNode.get(0).get("geometry").get("location").get("lng").asText();

        String startCoords = lng + ", "+ lat;

        return startCoords;
    }
    @GetMapping("/end")
    public String generateEndCoordinates() throws ApiException, JsonProcessingException {
        String end = serviceAI.end();
        String jsonData = GoogleMapsGeocoding.googleMaps(end);

        ObjectMapper mapper =  new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonData);

        String lat = jsonNode.get(0).get("geometry").get("location").get("lat").asText();
        String lng = jsonNode.get(0).get("geometry").get("location").get("lng").asText();

        String endCoords = lng + ", "+ lat;
        return endCoords;
    }


    @GetMapping("/option")
    public String generateOptionCoordinates() throws ApiException, JsonProcessingException, ThymeleafException {
        String start = serviceAI.start();
        String end = serviceAI.end();
        String optionCoords;

        if(start != null && end != null) {
            String option = gpt.usePrompt(serviceAI.getOption(start, end));
            String jsonData = GoogleMapsGeocoding.googleMaps(option);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(jsonData);

            String lat = jsonNode.get(0).get("geometry").get("location").get("lat").asText();
            String lng = jsonNode.get(0).get("geometry").get("location").get("lng").asText();

             optionCoords = lng + ", "+ lat;
        }else{
            throw new IllegalArgumentException("Invalid");
        }
        return optionCoords;
    }

}
