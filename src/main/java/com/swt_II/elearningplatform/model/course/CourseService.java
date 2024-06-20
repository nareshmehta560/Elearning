package com.swt_II.elearningplatform.model.course;

import com.swt_II.elearningplatform.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {

        this.courseRepository = courseRepository;
    }
    // Retrieve a course by its ID from the database
    public Course getCourseById(Long courseId) {

        return courseRepository.findById(courseId).orElse(null);
    }
    // Save a course along with its file content to the database
    public void saveCourse(Course course, byte[] fileContent) {
        course.setField(fileContent);
        courseRepository.save(course);
    }
    // Retrieve all courses from the database
    public List<Course> getAllCourses() {
        return (List<Course>) courseRepository.findAll();
    }
    public List<Course> getCoursesByCategory(String category) {
        return courseRepository.findByCategory(category);
    }
    public Course findByName(String name) {
        return courseRepository.findByName(name);
    }


    public List<Course> searchCoursesByKeyword(String keyword) {
        return courseRepository.findByNameContainingIgnoreCase(keyword);
    }

}


