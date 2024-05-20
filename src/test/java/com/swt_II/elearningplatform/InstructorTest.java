package com.swt_II.elearningplatform;
import com.swt_II.elearningplatform.model.role.Role;
import com.swt_II.elearningplatform.model.user.Instructor;
import com.swt_II.elearningplatform.model.user.InstructorService;
import com.swt_II.elearningplatform.model.role.RoleService;
import com.swt_II.elearningplatform.model.user.User;
import com.swt_II.elearningplatform.repositories.InstructorRepository;
import com.swt_II.elearningplatform.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class InstructorTest {

    @InjectMocks
    private InstructorService instructorService;

    @Mock
    private InstructorRepository instructorRepository;

    @Mock
    private RoleService roleService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testApproveInstructor() {
        User user = new User();
        user.setUserName("testUser2");
        user.setPassword("$2a$12$yI8wA5Kd0pcFknDHqyZgN.9/wAHmVzLRCpsBNuUiCSLazDJ4tYM8u");
        user.setEmail("test@gmail.com");
        user.addRole(roleService.getRoleFromRoleName("USER"));
        when(userRepository.findByUserName("testUser2")).thenReturn(user);


        Long id = 1L;
        Instructor instructor = new Instructor("MSc","5 years");
        instructor.setPaypalEmail("test@gmail.com");
        User user1 = userRepository.findByUserName("testUser2");
        instructor.setUser(user1);

        when(instructorRepository.findById(id)).thenReturn(Optional.of(instructor));

        instructorService.approveInstructor(id);
        assertTrue(user.getRoles().contains(roleService.getRoleFromRoleName("INSTRUCTOR")));
        assertTrue(instructor.isApproved()); // Check if the instructor is approved

        verify(instructorRepository, times(1)).findById(id);
        verify(instructorRepository, times(1)).save(instructor);
        verify(userRepository, times(1)).save(any());
    }
}