package com.example.JournAI.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "UserRegistration")

public class User {
    @Id
    @Column
    @SequenceGenerator(name="User_id_sequence", sequenceName = "User_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "User_id_sequence")

    private Long id;
    @Column
    private String username;
    @Column
    private String email;
    @Column(name = "user_password")
    private String user_password;

    @Column(name = "passwordResetToken")
    private String passwordResetToken;

    @Column(name = "passwordResetTokenExpiry")
    private LocalDateTime passwordResetTokenExpiry;

    public String getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(String passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    public LocalDateTime getPasswordResetTokenExpiry() {
        return passwordResetTokenExpiry;
    }

    public void setPasswordResetTokenExpiry(LocalDateTime passwordResetTokenExpiry) {
        this.passwordResetTokenExpiry = passwordResetTokenExpiry;
    }

    public User(Long id, String username, String email, String password, String passwordResetToken, LocalDateTime passwordResetTokenExpiry) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.user_password = password;
    }

    public User(String username, String email, String password, String passwordResetToken, LocalDateTime passwordResetTokenExpiry ) {
        this.username = username;
        this.email = email;
        this.user_password = password;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", user_password='" + user_password + '\'' +
                ", passwordResetToken='" + passwordResetToken + '\'' +
                ", passwordResetTokenExpiry=" + passwordResetTokenExpiry +
                '}';
    }
}

