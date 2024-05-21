package com.swt_II.elearningplatform.repositories;

import com.swt_II.elearningplatform.model.course.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
