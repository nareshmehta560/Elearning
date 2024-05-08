package com.swt_II.elearningplatform;

import com.swt_II.elearningplatform.controller.CourseController;
import com.swt_II.elearningplatform.model.course.Course;
import com.swt_II.elearningplatform.model.course.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CourseUploadTest {
/*
    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSubmitCourseForm() throws Exception {
        // Prepare mock course details and file
        String courseName = "Mathematics";
        String courseDescription = "Introduction to Algebra";
        String courseCategory = "Math";
        double coursePrice = 29.99;

        Course mockCourse = new Course();
        mockCourse.setName(courseName);
        mockCourse.setDescription(courseDescription);
        mockCourse.setCategory(courseCategory);
        mockCourse.setPrice(coursePrice);

        MultipartFile mockFile = new MockMultipartFile("file", "test.txt", "text/plain", "Test file content".getBytes());

        // Call the controller method
        courseController.submitCourseForm(mockCourse, mockFile);

        // Verify that courseService.saveCourse() was called with the expected Course object
        verify(courseService, times(1)).saveCourse(any(Course.class));

        // Capture the Course object passed to saveCourse() and perform assertions
        ArgumentCaptor<Course> courseCaptor = ArgumentCaptor.forClass(Course.class);
        verify(courseService).saveCourse(courseCaptor.capture());

        // Retrieve the captured Course object
        Course capturedCourse = courseCaptor.getValue();

        // Assert that the captured Course object has the expected values
        assertNotNull(capturedCourse);
        assertEquals(courseName, capturedCourse.getName());
        assertEquals(courseDescription, capturedCourse.getDescription());
        assertEquals(courseCategory, capturedCourse.getCategory());
        assertEquals(coursePrice, capturedCourse.getPrice());

    }

 */
}
