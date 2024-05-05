package com.example.elearningplatform.Controller;

import com.example.elearningplatform.Model.InstructorApplication;
import com.example.elearningplatform.Model.User;
import com.example.elearningplatform.Repository.InstructorApplicationRepository;
import com.example.elearningplatform.Util.ThymeleafApplicationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sql.rowset.serial.SerialBlob;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.SQLException;

@Controller
public class InstructorApplicationController {

    public ThymeleafApplicationForm applicationForm = new ThymeleafApplicationForm();
    @Autowired
    InstructorApplicationRepository instructorApplicationRepository;

    @Autowired
    InstructorApplicationController(InstructorApplicationRepository instructorApplicationRepository) {
        this.instructorApplicationRepository = instructorApplicationRepository;
    }
    @GetMapping("/Application")
    public String getApplicationForm(Model model) {
        model.addAttribute("applicationForm", applicationForm);
        return "Application";
    }

    @PostMapping("/Application")
    public String saveApplication(@RequestParam String title,
                                  @RequestParam String text,
                                  @RequestParam String pdf) {
        Blob pdfBlob;
        try {
            pdfBlob = new SerialBlob(pdf.getBytes(StandardCharsets.UTF_8));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        User user = getCurrentUser();
        //ToDo: validation, error check
        this.instructorApplicationRepository.save(new InstructorApplication(title, text, pdfBlob, user));
        return "redirect:/";
    }

    private User getCurrentUser() {
        //ToDo: get the current User from somewhere, maybe session state/key/whatever -> Login???
        return new User();
    }
}
