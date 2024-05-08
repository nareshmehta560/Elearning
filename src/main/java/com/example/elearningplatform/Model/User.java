package com.example.elearningplatform.Model;

import com.example.elearningplatform.Repository.UserRepository;
import jakarta.persistence.*;

import java.util.List;

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
    @OneToMany(mappedBy = "applicant")
    private List<InstructorApplication> instructorApplications;

    public User(){}
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public static User testUser() {
        return new User("testname", "testpass", "testemail");
    }

}
