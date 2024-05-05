package com.example.elearningplatform.Model;

import jakarta.persistence.*;

@Entity
@lombok.Getter
@lombok.Setter
@Table(name = "`user`")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`User_ID`")
    private Long id;
    @Column(name = "`username`", unique = true)
    private String username;
    @Column(name = "`password`")
    private String password;
    @Column(name = "`email`")
    private String email;

}
