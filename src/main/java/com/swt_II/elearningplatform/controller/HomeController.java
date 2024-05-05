package com.swt_II.elearningplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
    @GetMapping(value = {"","/","customLogin"})
    public String displayLogin() {
        return "customLogin";
    }
    @GetMapping(value = "/home")
    public  String displayHome() {
        return "home";
    }
}
