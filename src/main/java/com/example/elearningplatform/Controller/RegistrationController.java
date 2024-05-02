package com.example.elearningplatform.Controller;

import com.example.elearningplatform.Repository.UserRepository;
import com.example.elearningplatform.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepo;
    @GetMapping("/")
    public String index() {
        return "register";
    }
    @PostMapping ("/register")
    public String userRegistration(@ModelAttribute User user) {
        System.out.println(user.toString());
        //validate
        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        System.out.println(user.getUsername());
        System.out.println(user.getEmail());

        User user_inserted = userRepo.save(user);
        return "home";
    }
}
