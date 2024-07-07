package com.swt_II.elearningplatform.model.user;

import com.swt_II.elearningplatform.model.cart.Cart;
import com.swt_II.elearningplatform.model.course.Course;
import com.swt_II.elearningplatform.model.wishlist.Wishlist;
import com.swt_II.elearningplatform.model.role.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "user_data")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String userName;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    @Transient
    private String confirmPassword;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @OneToOne(mappedBy = "user", cascade=CascadeType.ALL)
    private Instructor instructor;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Wishlist wishlist;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "User_Course",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses;


    public User(String firstName, String lastName, String userName, String email, String password, String confirmPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public User() {

    }

    public static User testUser(Role role) {
        User user = testUser();
        user.addRole(role);
        return user;
    }
    public static User testUser() {
        User user = new User();
        user.setUserName("testUser2");
        user.setPassword("$2a$12$yI8wA5Kd0pcFknDHqyZgN.9/wAHmVzLRCpsBNuUiCSLazDJ4tYM8u");
        user.setEmail("test@gmail.com");
        return user;
    }

    public void addRole(Role role){
        if (this.roles == null) {
            this.roles = new ArrayList<>();
        }
        this.roles.add(role);
    }
    public void addCourse(Course course){
        if (this.courses == null) {
            this.courses = new HashSet<>();
        }
        this.courses.add(course);
    }
}
