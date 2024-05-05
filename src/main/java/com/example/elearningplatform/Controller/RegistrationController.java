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

   // @Autowired
    //private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    @PostMapping ("/register")
    public String userRegistration(@ModelAttribute User user, Model model) {
        //Checking if the username already exists
        User existingUser = userRepo.findByUsername(user.getUsername());
        if(existingUser != null) {
            model.addAttribute("error","Username already exists!");
            model.addAttribute("user", user);
            return "register";
        }

        //Checking if the email-address is already registered.
        User existingEmail = userRepo.findByEmail(user.getEmail());
        if(existingEmail != null) {
            model.addAttribute("error","Account with this email already exists!");
            model.addAttribute("user", user);
            return "register";
        }

        //Encode the password
        //user.setPassword(passwordEncoder.encode(user.getPassword()));

        //validate
        System.out.println(user.toString());
        System.out.println("Registration successfull!");

        User user_inserted = userRepo.save(user);
        return "customLogin";
    }
}
