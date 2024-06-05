package com.swt_II.elearningplatform.repositories;

import com.swt_II.elearningplatform.model.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByNameContaining(String keyword);
}

