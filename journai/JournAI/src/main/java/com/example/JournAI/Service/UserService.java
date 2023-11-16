package com.example.JournAI.Service;


import com.example.JournAI.Repository.UserRepository;
import com.example.JournAI.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;



    public boolean registerUser(String username, String email, String user_password) {

        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent() ){
            return false;
        }
        else{
            User newuser = new User();

            newuser.setUsername(username);
            newuser.setEmail(email);
            newuser.setUser_password(user_password);
            userRepository.save(newuser);

            return true;
        }

    }


    public String verifyUser(String username, String userPassword) {

        String results;

        Optional<User> user = userRepository.findByUsername(username);
        if((user.isPresent() && user.get().getUsername().equals(username))){
            if( user.get().getUser_password().equals(userPassword)){
                results = "verify";
                return results;
            }
            else{
                results = "userPassword";

                return results;
            }

        }

        results = "username";

        return results;
    }

    public boolean forgetPassWord(String email){
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            return true;
        }
        else{
            return false;
        }

    }

    public String updatePassword(String username,String password, String confirmPassword) {
        Optional<User> user = userRepository.findByUsername(username);

        String userName = user.get().getUsername();
        String results;
        if(userName.equals(username)) {

            if(password.equals(confirmPassword)) {
                user.get().setUser_password(confirmPassword);
                userRepository.save(user.get());

                results = "save";
                return results;
            }
    else{
                results = "confirmPassword";
                return results;
            }

        }
    else{
            results = "user";
            return results;
        }


    }


}