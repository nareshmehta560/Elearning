package com.swt_II.elearningplatform.controller;

import com.swt_II.elearningplatform.model.course.Course;
import com.swt_II.elearningplatform.model.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courseupload")
    public String showCourseForm(Model model) {
        model.addAttribute("newcourse", new Course());
        return "upload";
    }

    @PostMapping("/courseupload")
    public String submitCourseForm(@ModelAttribute("newcourse") Course course,
                                   @RequestParam("file") MultipartFile[] files) throws IOException {
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String originalFileName = file.getOriginalFilename();
                // Process the file (e.g., save to database, etc.)
                byte[] fileContent = file.getBytes(); // Read file content

                course.setName(originalFileName);
                course.setField(fileContent);
                courseService.saveCourse(course, fileContent);
                System.out.println("Uploaded file: " + originalFileName);
            }
        }

        return "redirect:/home";
    }

    @GetMapping("/downloadFile/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long id) {
        // Load file from database
        Course course = courseService.getCourseById(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + course.getName() + "\"")
                .body( new ByteArrayResource(course.getField()));
    }


}

