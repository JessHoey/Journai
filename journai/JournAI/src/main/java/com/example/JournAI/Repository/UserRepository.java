package com.example.JournAI.Repository;

import com.example.JournAI.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM User_Registration WHERE username = :username", nativeQuery = true)
    Optional<User> findByUsername(String username);


    @Query(value = "SELECT * FROM User_Registration WHERE email = :email", nativeQuery = true)
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM User_Registration WHERE passwordResetToken = :passwordResetToken", nativeQuery = true)
    Optional<User> findByResetPasswordToken(String passwordResetToken);




//    @Query(value = "SELECT * FROM User_Registration WHERE username = :username a", nativeQuery = true)
//    Optional<User> findByLoginAndPassword(String Login, String password);
}