package com.example.elearningplatform.model.course;

import com.example.elearningplatform.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public void saveCourse(Course course) {
        courseRepository.save(course);
    }
}


