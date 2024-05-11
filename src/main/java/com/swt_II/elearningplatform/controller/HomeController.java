package com.swt_II.elearningplatform.controller;

import com.swt_II.elearningplatform.model.course.Course;
import com.swt_II.elearningplatform.model.course.CourseService;
import com.swt_II.elearningplatform.model.user.User;
import com.swt_II.elearningplatform.repositories.CourseRepository;
import com.swt_II.elearningplatform.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    @GetMapping(value = "/uploadCourse")
    public String upload(Model model) {
        model.addAttribute("newcourse", new Course());
        return "upload";
    }
    @Autowired
    private CourseService courseService;

    @GetMapping("/home")
    public String showHomePage(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "home";
    }
}
