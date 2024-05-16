package com.swt_II.elearningplatform;
import com.swt_II.elearningplatform.model.course.Course;
import com.swt_II.elearningplatform.model.course.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CourseUploadTest {

    @Autowired
    private CourseService courseService;

    @Test
    public void testCourseUpload() throws Exception {
        Course course = new Course();
        course.setName("Test Course");
        course.setCategory("Test category");
        course.setDescription("Test course is most important");
        course.setPrice(12.98);

        MultipartFile file = new MockMultipartFile("file", "hello.txt", "text/plain", "Hello, World!".getBytes());
        courseService.saveCourse(course, file.getBytes());

        Course uploadedCourse = courseService.getCourseById(course.getId());
        assertNotNull(uploadedCourse, "Course should be uploaded");
    }
}
