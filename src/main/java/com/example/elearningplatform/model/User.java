package com.example.elearningplatform.model;

import jakarta.persistence.*;

@Entity
@lombok.Getter
@lombok.Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String firstName;
    private String lastName;
    private String username;
    private String email;


    private String password;
    private String confirmPassword;

    public User(String firstName, String lastName, String username, String email, String password, String confirmPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public User() {

    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }

}