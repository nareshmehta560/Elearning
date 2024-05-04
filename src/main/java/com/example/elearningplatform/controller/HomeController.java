package com.example.elearningplatform.controller;

import com.example.elearningplatform.model.course.Course;
import com.example.elearningplatform.model.user.User;
import com.example.elearningplatform.repositories.CourseRepository;
import com.example.elearningplatform.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(value = {"","/","customLogin"})
    public String displayLogin() {
        return "customLogin";
    }
    @GetMapping(value = "/home")
    public  String displayHome(Model model) {
        Iterable<User> users = userRepository.findAll();
        Iterable<Course> courses = courseRepository.findAll();

        model.addAttribute("users", users);
        model.addAttribute("courses", courses);
        return "home";
    }
    @GetMapping(value = "/uploadCourse")
    public String upload() {
        return "upload";
    }
}
