package com.example.elearningplatform.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InstructorApplicationController {

    @GetMapping("/Application")
    public String getApplication()
    {
        //ToDo: implementation
        return "Application";
    }
}
