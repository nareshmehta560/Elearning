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
    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }
    public void saveCourse(Course course, byte[] fileContent) {
        course.setField(fileContent);
        courseRepository.save(course);
    }
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}


