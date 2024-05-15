package com.swt_II.elearningplatform.controller;

import com.swt_II.elearningplatform.model.course.Course;
import com.swt_II.elearningplatform.model.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @GetMapping(value = {"","/"})
    public String displayLogin() {
        return "home";
    }
    @GetMapping(value = "/home")
    public  String displayHome() {
        return "home";
    }
    @GetMapping(value = "/customLogin")
    public String displayCustomLogin() {
        return "customLogin";
    }
    @GetMapping(value = "/dashboard")
    public String displayDashboard() {
        return "dashboard";
    }
    @GetMapping(value = "/registration")
    public String displayRegister() {
        return "register";
    }

    // Bibek
    @GetMapping(value = "/uploadCourse")
    public String upload(Model model) {
        model.addAttribute("newcourse", new Course());
        return "upload";
    }
    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/courselist")
    public String showHomePage(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "courselist";
    }
}
