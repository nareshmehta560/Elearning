package com.swt_II.elearningplatform.controller;

import com.swt_II.elearningplatform.model.user.Instructor;
import com.swt_II.elearningplatform.model.user.InstructorService;
import com.swt_II.elearningplatform.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class InstructorController {
    @Autowired
    private InstructorService instructorService;


    @GetMapping(value = "/newInstructors")
    public String approveInstructor(Model model) {
        model.addAttribute("instructors", instructorService.getUnapprovedInstructors());
        return "newInstructors";
    }

    @PostMapping(value = "/approveInstructor")
    public String approveInstructor(@RequestParam Long id) {
        instructorService.approveInstructor(id);
        return "redirect:/home";
    }

    @PostMapping(value = "/deleteInstructor")
    public String deleteInstructor(@RequestParam Long id) {
        instructorService.deleteInstructor(id);
        return "redirect:/newInstructors";
    }
}
