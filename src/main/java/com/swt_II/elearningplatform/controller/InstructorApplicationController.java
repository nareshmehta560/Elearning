package com.swt_II.elearningplatform.controller;

import com.swt_II.elearningplatform.model.InstructorApplication;
import com.swt_II.elearningplatform.model.User;
import com.swt_II.elearningplatform.repositories.InstructorApplicationRepository;
import com.swt_II.elearningplatform.repositories.UserRepository;
import com.swt_II.elearningplatform.util.ThymeleafApplicationForm;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @GetMapping("/CSS/application.css")
    public String getCss() {
        return "CSS/Application.css";
    }
    @GetMapping("/Application")
    public String getApplicationForm(Model model) {
        model.addAttribute("applicationForm", applicationForm);
        return "Application";
    }

    @PostMapping("/Application")
    public String saveApplication(@RequestParam String title,
                                  @RequestParam String text,
                                  @RequestParam MultipartFile pdf,
                                  final RedirectAttributes attributes) {

        StringBuilder stringBuilder = new StringBuilder();
        boolean success = true;
        if(!(pdf.getName().equals("pdf"))) {
            success = false;
            stringBuilder.append("only PDF allowed!. ");
        }
        if(pdf.getSize() > 100000000) {
            success = false;
            stringBuilder.append("pdf too big, MAX_Size = 100MB. ");
        }
        if(title.length() > 45) {
            success = false;
            stringBuilder.append("wrong title-length, MAX = 45. ");
        }
        if(title.isEmpty()) {
            success = false;
            stringBuilder.append("title is required, is not allowed to be empty. ");
        }
        if(text.length() > 16777216) {
            success = false;
            stringBuilder.append("wrong text-length, consider using Fileupload, if text is a lot. ");
        }
        if(pdf.getSize() == 0 && text.isEmpty()) {
            success = false;
            stringBuilder.append("Application has no content, content is required. ");
        }

        if(success) {
            try {
                Blob pdfBlob = new SerialBlob(pdf.getBytes());
                String filename = pdf.getOriginalFilename() != null ? pdf.getOriginalFilename() : "nullName." + pdf.getName();
                long user = getCurrentUser();
                this.instructorApplicationRepository.save(
                        new InstructorApplication(title, text, pdfBlob, filename, userRepository.findById(user).orElseThrow()));
                attributes.addFlashAttribute("success","saved successfully.");
                return "redirect:/";
            } catch (SQLException | IOException e) {
                stringBuilder.append("Internal server error: failed to parse pdf into SerialBlob.");
            }
        }

        //test, if MultipartFile-data comes in correctly:
        //boolean bool = saveBlobAsFile(pdfBlob, filename);

        attributes.addFlashAttribute("error", stringBuilder.toString());
        return "redirect:/Application";
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
