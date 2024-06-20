package com.swt_II.elearningplatform.controller;

import com.swt_II.elearningplatform.model.course.Course;
import com.swt_II.elearningplatform.model.course.CourseService;
import com.swt_II.elearningplatform.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;
    // Show course upload form
    @GetMapping("/courseupload")
    public String showCourseForm(Model model) {
        model.addAttribute("newcourse", new Course());
        return "upload";
    }
    // Handle course form submission and file upload
    @PostMapping("/courseupload")
    public String submitCourseForm(@ModelAttribute("newcourse") Course course,
                                   @RequestParam("file") MultipartFile[] files, RedirectAttributes redirectAttributes) throws IOException {
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String originalFileName = file.getOriginalFilename();
                byte[] fileContent = file.getBytes();

                course.setField(fileContent);
                course.setFileName(originalFileName);
                courseService.saveCourse(course, fileContent);
                System.out.println("Uploaded file: " + originalFileName);
            }
        }
        // Add a flash attribute for success message
        redirectAttributes.addFlashAttribute("message", "File upload successful!");
        return "redirect:/home";
    }
    // Download file by course ID
    @GetMapping("/downloadFile/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long id) {
        // Load file from database
        Course course = courseService.getCourseById(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + course.getFileName() + "\"")
                .body( new ByteArrayResource(course.getField()));
    }
    @Autowired
    private CourseRepository courseRepository;
    //Handles fetching and displaying courses on the homepage.

}

