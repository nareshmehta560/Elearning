package com.swt_II.elearningplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
    @GetMapping(value = {"","/"})
    public String displayLogin() {
        return "home";
    }
    @GetMapping(value = "/home")
    public  String displayHome() {
        return "home";
    }
    @GetMapping(value = "/customLogin")
    public String displayCustomLogin() {
        return "customLogin";
    }
    @GetMapping(value = "/dashboard")
    public String displayDashboard() {
        return "dashboard";
    }
    @GetMapping(value = "/registration")
    public String displayRegister() {
        return "register";
    }
}
