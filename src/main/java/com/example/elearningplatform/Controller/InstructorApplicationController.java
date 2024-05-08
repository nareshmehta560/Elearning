package com.example.elearningplatform.Controller;

import com.example.elearningplatform.Model.InstructorApplication;
import com.example.elearningplatform.Model.User;
import com.example.elearningplatform.Repository.InstructorApplicationRepository;
import com.example.elearningplatform.Repository.UserRepository;
import com.example.elearningplatform.Util.ThymeleafApplicationForm;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

@Controller
public class InstructorApplicationController {

    public ThymeleafApplicationForm applicationForm = new ThymeleafApplicationForm();
    @Autowired
    InstructorApplicationRepository instructorApplicationRepository;

    @Autowired
    UserRepository userRepository;

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
                                  @RequestParam MultipartFile pdf) throws IOException, SQLException {

        Blob pdfBlob = new SerialBlob(pdf.getBytes());
        String filename = pdf.getOriginalFilename() != null ? pdf.getOriginalFilename() : "nullName." + pdf.getName();

        //test, if MultipartFile-data comes in correctly:
        boolean bool = saveBlobAsFile(pdfBlob, filename);

        long user = getCurrentUser();
        //ToDo: add issue number to Branch and commits
        //ToDo: validation, error check

        this.instructorApplicationRepository.save(
                new InstructorApplication(title, text, pdfBlob, filename, userRepository.findById((int) user).orElseThrow()));
        return "redirect:/";
    }

    /***
     *
     * @param pdfBlob
     * @param filename
     * @return true, if successful
     */
    private boolean saveBlobAsFile(Blob pdfBlob, String filename) {
        boolean successful = true;
        File outputFile = new File(filename);
        try {
            FileOutputStream fOut = new FileOutputStream(outputFile);
            IOUtils.copy(pdfBlob.getBinaryStream(), fOut);
            IOUtils.closeQuietly(fOut);
        } catch (IOException | SQLException e) {
            successful = false;
        }
        return successful;
    }

    private long getCurrentUser() {
        //ToDo: get the current User from somewhere, maybe session state/key/whatever -> Login???
        //Workaround for now:
        if(this.userRepository.exists(Example.of(User.testUser())) == false) {
            this.userRepository.save(User.testUser());
        }

        return this.userRepository.findAll(Example.of(User.testUser())).get(0).getId();
    }
}
