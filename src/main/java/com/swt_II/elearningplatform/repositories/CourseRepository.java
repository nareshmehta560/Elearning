package com.swt_II.elearningplatform.repositories;

import com.swt_II.elearningplatform.model.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CourseRepository extends JpaRepository<Course, Long> {
    }



