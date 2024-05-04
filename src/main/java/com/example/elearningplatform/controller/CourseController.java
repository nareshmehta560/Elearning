package com.example.elearningplatform.controller;

import com.example.elearningplatform.model.course.Course;
import com.example.elearningplatform.model.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courseupload")
    public String showCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "upload";
    }

    @PostMapping("/courseupload")
    public String submitCourseForm(@ModelAttribute("newcourse") Course course) {
        courseService.saveCourse(course);
        return "redirect:/home";
    }

}

