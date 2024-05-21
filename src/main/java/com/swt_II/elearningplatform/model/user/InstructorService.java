package com.swt_II.elearningplatform.model.user;

import com.swt_II.elearningplatform.model.role.RoleService;
import com.swt_II.elearningplatform.repositories.InstructorRepository;
import com.swt_II.elearningplatform.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {
    private final InstructorRepository instructorRepository;
    private final RoleService roleService;
    private final UserRepository userRepository;

    private final UserDetailsService userDetailsService;

    public InstructorService(InstructorRepository instructorRepository, RoleService roleService, UserRepository userRepository,UserDetailsService userDetailsService) {
        this.instructorRepository = instructorRepository;
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
    }
    public List<Instructor> getUnapprovedInstructors() {
        return instructorRepository.findAllByIsApprovedFalse();
    }
    public void approveInstructor(Long id) {
        Instructor instructor = instructorRepository.findById(id).get();
        instructor.setApproved(true);
        instructorRepository.save(instructor);
        User user = instructor.getUser();
         user.addRole(roleService.getRoleFromRoleName("INSTRUCTOR"));
         userRepository.save(user);
        // Update authorities in current session
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
        Authentication newAuth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }

    public void deleteInstructor(Long id) {
        Instructor instructor = instructorRepository.findById(id).get();
        instructorRepository.delete(instructor);
    }
}
