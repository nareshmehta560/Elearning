package com.swt_II.elearningplatform;

import com.swt_II.elearningplatform.model.course.Course;
import com.swt_II.elearningplatform.model.course.CourseService;
import com.swt_II.elearningplatform.repositories.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class CourseSearchTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchCoursesByKeyword() {
        // Mock data
        Course course1 = new Course();
        course1.setName("Java Basics");
        Course course2 = new Course();
        course2.setName("Advanced Java");

        // Define mock behavior for the repository
        when(courseRepository.findByNameContainingIgnoreCase("java"))
                .thenReturn(Arrays.asList(course1, course2));
        when(courseRepository.findByNameContainingIgnoreCase("python"))
                .thenReturn(Collections.emptyList());

        // Positive case: search for "java"
        List<Course> resultPositive = courseService.searchCoursesByKeyword("java");
        assertEquals(2, resultPositive.size(), "Expected 2 courses to be found");

        // Check if the results contain the expected courses, regardless of order
        assertTrue(resultPositive.stream().anyMatch(course -> "Java Basics".equals(course.getName())), "Expected to find 'Java Basics'");
        assertTrue(resultPositive.stream().anyMatch(course -> "Advanced Java".equals(course.getName())), "Expected to find 'Advanced Java'");

        // Negative case: search for "python"
        List<Course> resultNegative = courseService.searchCoursesByKeyword("python");
        assertTrue(resultNegative.isEmpty(), "Expected no courses to be found for 'python'");
    }

    @Test
    public void testSearchCoursesByKeywordWithNoResults() {
        // Define mock behavior for the repository
        when(courseRepository.findByNameContainingIgnoreCase("nonexistent"))
                .thenReturn(Collections.emptyList());

        // Negative case: search for "nonexistent"
        List<Course> result = courseService.searchCoursesByKeyword("nonexistent");
        assertTrue(result.isEmpty(), "Expected no courses to be found for 'nonexistent'");
    }

    @Test
    public void testSearchCoursesByKeywordWithMixedCase() {
        // Mock data
        Course course1 = new Course();
        course1.setName("Java Basics");
        Course course2 = new Course();
        course2.setName("Advanced Java");

        // Define mock behavior for the repository
        when(courseRepository.findByNameContainingIgnoreCase("JAVA"))
                .thenReturn(Arrays.asList(course1, course2));

        // Case insensitive search for "JAVA"
        List<Course> result = courseService.searchCoursesByKeyword("JAVA");
        assertEquals(2, result.size(), "Expected 2 courses to be found");
    }

    @Test
    public void testSearchCoursesByKeywordWithPartialMatch() {
        // Mock data
        Course course1 = new Course();
        course1.setName("Java Basics");
        Course course2 = new Course();
        course2.setName("Advanced Java");

        // Define mock behavior for the repository
        when(courseRepository.findByNameContainingIgnoreCase("Java"))
                .thenReturn(Arrays.asList(course1, course2));

        // Partial match search for "Java"
        List<Course> result = courseService.searchCoursesByKeyword("Java");
        assertEquals(2, result.size(), "Expected 2 courses to be found");
    }
}