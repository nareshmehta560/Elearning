package com.example.elearningplatform.model.user;

import jakarta.persistence.*;

@Entity
@lombok.Getter
@lombok.Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String email;

}
