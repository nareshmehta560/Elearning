package com.example.elearningplatform.repositories;

import com.example.elearningplatform.model.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CourseRepository extends JpaRepository<Course, Long> {
    }



