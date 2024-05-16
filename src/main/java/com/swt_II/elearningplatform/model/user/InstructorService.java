package com.swt_II.elearningplatform.model.user;

import com.swt_II.elearningplatform.model.role.RoleService;
import com.swt_II.elearningplatform.repositories.InstructorRepository;
import com.swt_II.elearningplatform.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {
    private final InstructorRepository instructorRepository;
    private final RoleService roleService;
    private final UserRepository userRepository;

    public InstructorService(InstructorRepository instructorRepository, RoleService roleService, UserRepository userRepository) {
        this.instructorRepository = instructorRepository;
        this.roleService = roleService;
        this.userRepository = userRepository;
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
    }

    public void deleteInstructor(Long id) {
        Instructor instructor = instructorRepository.findById(id).get();
        instructorRepository.delete(instructor);
    }
}
