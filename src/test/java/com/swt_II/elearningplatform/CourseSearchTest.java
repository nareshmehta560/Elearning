package com.swt_II.elearningplatform;

import com.swt_II.elearningplatform.model.course.Course;
import com.swt_II.elearningplatform.model.course.CourseService;
import com.swt_II.elearningplatform.repositories.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseSearchTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    private Course course1;
    private Course course2;

    @BeforeEach
    public void setUp() {
        // Initialize mock data
        course1 = new Course();
        course1.setName("Java Basics");

        course2 = new Course();
        course2.setName("Advanced Java");

        // Define mock behavior for findByNameContainingIgnoreCase method
        when(courseRepository.findByNameContainingIgnoreCase("java"))
                .thenReturn(Arrays.asList(course1, course2));
        when(courseRepository.findByNameContainingIgnoreCase("python"))
                .thenReturn(Collections.emptyList());
    }

    @Test
    public void testSearchCoursesByKeyword() {
        // Positive case: search for "java"
        List<Course> resultPositive = courseService.searchCoursesByKeyword("java");
        assertEquals(2, resultPositive.size(), "Expected 2 courses to be found");

        // Check if the results contain the expected courses, regardless of order
        boolean hasJavaBasics = resultPositive.stream().anyMatch(course -> "Java Basics".equals(course.getName()));
        boolean hasAdvancedJava = resultPositive.stream().anyMatch(course -> "Advanced Java".equals(course.getName()));
        assertTrue(hasJavaBasics, "Expected to find 'Java Basics'");
        assertTrue(hasAdvancedJava, "Expected to find 'Advanced Java'");

        // Negative case: search for "python"
        List<Course> resultNegative = courseService.searchCoursesByKeyword("python");
        assertTrue(resultNegative.isEmpty(), "Expected no courses to be found for 'python'");
    }
}