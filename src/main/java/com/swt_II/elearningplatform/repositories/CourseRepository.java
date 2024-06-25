package com.swt_II.elearningplatform.repositories;

import com.swt_II.elearningplatform.model.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    // Existierende Methoden
    @Query("SELECT DISTINCT c.category FROM Course c")
    List<String> findDistinctCategories();

    @Query("SELECT c FROM Course c WHERE c.category = :category")
    List<Course> findByCategory(@Param("category") String category);

    Course findByName(String name);

    @Query("SELECT c FROM Course c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Course> findByNameContainingIgnoreCase(@Param("keyword") String keyword);

}

