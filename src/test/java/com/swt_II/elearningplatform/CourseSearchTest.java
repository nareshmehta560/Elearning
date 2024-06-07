package com.swt_II.elearningplatform;

import com.swt_II.elearningplatform.model.course.Course;
import com.swt_II.elearningplatform.model.course.CourseService;
import com.swt_II.elearningplatform.repositories.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test") // Ensure you're using a test profile
public class CourseSearchTest {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void testSearchCoursesByKeyword() {
        // Setup
        Course  course1 = new Course();
        course1.setName("Java Basics");
        Course course2 = new Course();
        course2.setName("Advanced Java");
        courseRepository.save(course1);
        courseRepository.save(course2);

        // Positive case: search for "java"
        List<Course> resultPositive = courseService.searchCoursesByKeyword("java");
        assertEquals(2, resultPositive.size());
        assertEquals("Java Basics", resultPositive.get(0).getName());
        assertEquals("Advanced Java", resultPositive.get(1).getName());

        // Negative case: search for "python"
        List<Course> resultNegative = courseService.searchCoursesByKeyword("python");
        assertTrue(resultNegative.isEmpty());
    }
}
