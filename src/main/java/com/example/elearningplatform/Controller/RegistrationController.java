package com.example.elearningplatform.Controller;

import com.example.elearningplatform.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @GetMapping("/")
    public String index() {
        return "register";
    }
    @PostMapping ("/register")
    public String userRegistration(@ModelAttribute User user) {
        System.out.println(user.toString());
        return "register";
    }
}
