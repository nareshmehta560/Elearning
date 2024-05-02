package com.example.elearningplatform.model.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
    public class CourseService {

        @Autowired
        private CourseRepository courseRepository;

        @Autowired
        private CourseFileRepository courseFileRepository;

        public Course uploadCourse(String courseName, String description, List<MultipartFile> files, double price) throws IOException {
            Course course = new Course();
            course.setCourseName(courseName);
            course.setDescription(description);
            course.setPrice(price);

            List<CourseFile> courseFiles = new ArrayList<>();

            for (MultipartFile file : files) {
                CourseFile courseFile = new CourseFile();
                courseFile.setFileName(file.getOriginalFilename());
                courseFile.setFileType(file.getContentType());
                courseFile.setCourse(course);

                courseFiles.add(courseFile);

                // Save file to a directory (if needed)
                // file.transferTo(new File("/path/to/save/" + file.getOriginalFilename()));
                // Save file to a directory on the server
                String uploadDirectory = "upload";
                String fileName = file.getOriginalFilename();
                String filePath = uploadDirectory + fileName;

                try {
                    // Create directories if they do not exist
                    File directory = new File(uploadDirectory);
                    if (!directory.exists()) {
                        directory.mkdirs();
                    }

                    // Save the file to the specified path
                    Path path = Paths.get(filePath);
                    Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("File saved");
                } catch (IOException ex) {
                    throw new IOException("Failed to save file " + fileName, ex);
                }
            }

            course.setFiles(courseFiles);

            return courseRepository.save(course);
        }
    }


