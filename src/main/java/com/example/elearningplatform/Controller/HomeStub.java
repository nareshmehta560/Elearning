package com.example.elearningplatform.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeStub {
    @GetMapping("/Home")
    String showHomePage() {
        return "Home";
    }
    @GetMapping("/")
    String showIndexHomePage() {
        return "Home";
    }
}
