package com.swt_II.elearningplatform.controller;

import com.swt_II.elearningplatform.model.course.Course;
import com.swt_II.elearningplatform.model.course.CourseService;
import com.swt_II.elearningplatform.model.user.User;
import com.swt_II.elearningplatform.model.user.UserService;
import com.swt_II.elearningplatform.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);


    @Autowired
    private DaoAuthenticationProvider authenticationProvider;


    //Handles fetching and displaying courses on the homepage.
    @GetMapping(value = {"","/","/home"})
    public String home(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        User user = userService.getCurrentUser();
        model.addAttribute("user",user);
        return "home";
    }

    @GetMapping(value = "/customLogin")
    public String displayCustomLogin() {
        return "customLogin";
    }
    @GetMapping(value = "/dashboard")
    public String displayDashboard(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("user",user);
        return "dashboard";
    }



    // Bibek
    @GetMapping(value = "/uploadCourse")
    public String upload(Model model ,Authentication authentication) {
        authentication.getAuthorities().forEach(authority -> logger.info("Authority: {}", authority.getAuthority()));

        boolean isUserInstructor = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_INSTRUCTOR"));

        if (isUserInstructor) {
            model.addAttribute("newcourse", new Course());
            return "upload";
        } else {
            model.addAttribute("user",userService.getCurrentUser());
            model.addAttribute("errorInstructor", "You dont have Instructor Right Apply for instructor");
            return "dashboard";
        }
    }
    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/courselist")
    public String showHomePage(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "courselist";
    }

    //request for searchCourse
    @GetMapping(value = "/search")
    public String searchCourses(@RequestParam(required = false) String keyword, Model model) {
        List<Course> courses;
        if (keyword == null || keyword.isEmpty()) {
            // Handle case where keyword is empty or null
            courses = courseService.getAllCourses();
        } else {
            courses = courseService.searchCoursesByKeyword(keyword);
        }

        if (courses.isEmpty()) {
            // No courses found, set a message to display in the template
            model.addAttribute("noCoursesMessage", "No courses found for the keyword: " + keyword);
        } else {
            // Courses found, add them to the model
            model.addAttribute("courses", courses);
        }
       model.addAttribute("user",userService.getCurrentUser());
        return "home";
    }

    //request for all courses in Home

    @GetMapping(value = "/homePage")
    public String displayHomePage(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        model.addAttribute("user",userService.getCurrentUser());
        return "home";
    }

}
