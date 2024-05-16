package com.swt_II.elearningplatform.controller;

import com.swt_II.elearningplatform.model.user.User;
import com.swt_II.elearningplatform.repositories.RoleRepository;
import com.swt_II.elearningplatform.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;

   @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/register")
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.addRole(roleRepo.findByRoleName("USER"));

        //validate
        User user_inserted = userRepo.save(user);
        model.addAttribute("success", "User has been registered successfully!");
        return "customLogin";
    }
}
