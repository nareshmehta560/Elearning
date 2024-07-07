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
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@Service
public class InstructorService {
    private final InstructorRepository instructorRepository;
    private final RoleService roleService;
    private final UserRepository userRepository;

    private final UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

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

    }
    public boolean haveAlreadyApplied(Long id) {
        return instructorRepository.existsInstructorByUser_Id(id);
    }

    public void deleteInstructor(Long id) {
        Instructor instructor = instructorRepository.findById(id).get();
        instructorRepository.delete(instructor);
    }

    /**
     * save the incoming Form-data into the DB
     * @param title small text
     * @param paypal small text
     * @param text large text
     * @param pdf large MultiPartFile
     * @param currentUser needed for User/Application saving
     * @return if unsuccessful throws error, else returns success message
     * @throws SQLException
     * @throws IOException
     */
    public String saveInstructorApplication(String title, String paypal, String text, MultipartFile pdf, User currentUser) throws SQLException, IOException {
        byte[] pdfBlob = pdf.getBytes();
        String filename = pdf.getOriginalFilename() != null ? pdf.getOriginalFilename() : "nullName." + pdf.getName();
        String successText = "Instructor Application saved successfully.";
        if(currentUser.getInstructor() != null && this.instructorRepository.existsById(currentUser.getInstructor().getId())) {
            deleteInstructor(currentUser.getInstructor().getId());
            successText = "Instructor Application updated successfully.";
        }
        User user = this.userRepository.findByUserName(currentUser.getUserName());
        //this.userRepository.deleteById(user.getId());
        user.setInstructor(new Instructor(title, pdfBlob, filename, text, paypal, user));
        this.userRepository.save(user);
        //this.instructorRepository.save(new Instructor(title, pdfBlob, filename, text, paypal, currentUser));

        return successText;
    }
}
