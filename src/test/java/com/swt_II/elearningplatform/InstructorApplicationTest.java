package com.swt_II.elearningplatform;


import com.swt_II.elearningplatform.controller.InstructorApplicationController;
import com.swt_II.elearningplatform.model.role.Role;
import com.swt_II.elearningplatform.model.role.RoleService;
import com.swt_II.elearningplatform.model.user.Instructor;
import com.swt_II.elearningplatform.model.user.InstructorService;
import com.swt_II.elearningplatform.model.user.User;
import com.swt_II.elearningplatform.repositories.InstructorRepository;
import com.swt_II.elearningplatform.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class InstructorApplicationTest {

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    @Transactional
    public void testInstructorApplication() {
        User user = User.testUser(new Role("USER"));
        String title = "title";
        String paypal = "paypal";
        String text = "import com.swt_II.elearningplatform.model.user.InstructorService;\n" +
                "import com.swt_II.elearningplatform.model.user.User;\n" +
                "import com.swt_II.elearningplatform.repositories.InstructorRepository;\n" +
                "import com.swt_II.elearningplatform.repositories.UserRepository;\n" +
                "import jakarta.transaction.Transactional;\n" +
                "import org.junit.jupiter.api.BeforeEach;\n" +
                "import org.junit.jupiter.api.Test;\n" +
                "import org.mockito.InjectMocks;\n" +
                "import org.mockito.Mock;\n" +
                "import org.mockito.MockitoAnnotations;\n" +
                "import org.springframework.security.core.userdetails.UserDetailsService;";
        MultipartFile multipartFile = new MockMultipartFile("filename", "content".getBytes());
        if(!userRepository.existsUserByUserName(user.getUserName())) {
            userRepository.save(user);
        }

        assertTrue(userRepository.existsUserByUserName(user.getUserName()));

        User userDB = userRepository.findByUserName(user.getUserName());
        assertEquals(user.getUserName(), userDB.getUserName());

        boolean saved;
        try {
            instructorService.saveInstructorApplication(title, paypal, text, multipartFile, user);
            saved = true;
        } catch (SQLException | IOException e) {
            saved = false;
        }

        assertTrue(saved);

        Instructor instructorDB = userDB.getInstructor();
        List<Instructor> instructorList = instructorService.getUnapprovedInstructors();
        assertEquals(title, instructorDB.getTitle());
        assertEquals(text, instructorDB.getQualificationAndExperience());
        assertEquals(multipartFile.getOriginalFilename(), instructorDB.getFileName());
    }

}
