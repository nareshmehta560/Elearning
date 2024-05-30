package com.swt_II.elearningplatform;
import com.swt_II.elearningplatform.model.role.Role;
import com.swt_II.elearningplatform.model.user.Instructor;
import com.swt_II.elearningplatform.model.user.InstructorService;
import com.swt_II.elearningplatform.model.role.RoleService;
import com.swt_II.elearningplatform.model.user.User;
import com.swt_II.elearningplatform.model.user.UserService;
import com.swt_II.elearningplatform.repositories.InstructorRepository;
import com.swt_II.elearningplatform.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class InstructorTest {
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName())).collect(Collectors.toList());
    }

    @InjectMocks
    private InstructorService instructorService;

    @Mock
    private InstructorRepository instructorRepository;

    @Mock
    private RoleService roleService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserDetailsService userDetailsService;


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @Transactional
    public void testApproveInstructor() {
        User user = User.testUser(roleService.getRoleFromRoleName("USER"));
        when(userRepository.findByUserName("testUser2")).thenReturn(user);
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("USER"));
        when(roleService.getRolesFromUserId(user)).thenReturn(roles);



        Long id = 1L;
        User user1 = userRepository.findByUserName("testUser2");
        Instructor instructor = Instructor.testInstructor(user1);

        when(instructorRepository.findById(id)).thenReturn(Optional.of(instructor));
        // Mock RoleService and UserDetailsServic
        when(userDetailsService.loadUserByUsername(user.getUserName())).thenReturn( new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),mapRolesToAuthorities(roles)));




        instructorService.approveInstructor(id);
        assertTrue(user.getRoles().contains(roleService.getRoleFromRoleName("INSTRUCTOR")));
        assertTrue(instructor.isApproved()); // Check if the instructor is approved

        verify(instructorRepository, times(1)).findById(id);
        verify(instructorRepository, times(1)).save(instructor);
        verify(userRepository, times(1)).save(any());
        verify(userDetailsService, times(1)).loadUserByUsername(user.getUserName());

    }
}