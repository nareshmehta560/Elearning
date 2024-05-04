package com.example.elearningplatform.repositories;

import com.example.elearningplatform.model.course.CourseFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseFileRepository extends JpaRepository<CourseFile, Long> {
}
