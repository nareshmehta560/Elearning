package com.example.elearningplatform.controller;

import com.example.elearningplatform.model.course.Course;
import com.example.elearningplatform.model.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadCourse(@RequestParam("courseName") String courseName,
                                          @RequestParam("description") String description,
                                          @RequestParam("price") double price,
                                          @RequestParam("files") List<MultipartFile> files) {
        try {
            Course course = courseService.uploadCourse(courseName, description, files, price);
            System.out.println(course);
            return ResponseEntity.ok().body("Course '" + course.getCourseName() + "' uploaded successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload course.");
        }
    }

}

