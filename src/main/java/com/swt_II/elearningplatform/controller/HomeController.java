package com.swt_II.elearningplatform.controller;

import com.swt_II.elearningplatform.model.cart.Cart;
import com.swt_II.elearningplatform.model.cart.CartService;
import com.swt_II.elearningplatform.model.course.Course;
import com.swt_II.elearningplatform.model.course.CourseService;
import com.swt_II.elearningplatform.model.user.User;
import com.swt_II.elearningplatform.model.user.UserService;
import com.swt_II.elearningplatform.model.wishlist.Wishlist;
import com.swt_II.elearningplatform.model.wishlist.WishlistService;
import com.swt_II.elearningplatform.repositories.CartRepository;
import com.swt_II.elearningplatform.repositories.CourseRepository;
import com.swt_II.elearningplatform.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);


    @Autowired
    private DaoAuthenticationProvider authenticationProvider;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartService cartService;

    @GetMapping(value = "/customLogin")
    public String displayCustomLogin() {
        return "customLogin";
    }
    @GetMapping(value = "/dashboard")
    public String displayDashboard() {
        return "dashboard";
    }


    // Bibek
    @GetMapping(value = "/uploadCourse")
    public String upload(Model model ,Authentication authentication) {
        authentication.getAuthorities().forEach(authority -> logger.info("Authority: {}", authority.getAuthority()));

        boolean isUserInstructor = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_INSTRUCTOR"));

        if (isUserInstructor) {
            model.addAttribute("newcourse", new Course());
            return "upload";
        } else {
            model.addAttribute("errorInstructor", "You dont have Instructor Right Apply for instructor");
            return "dashboard";
        }
    }


    @GetMapping(value = "/courselist")
    public String showHomePage(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "courselist";
    }

    @Autowired
    private CourseRepository courseRepository;
    @GetMapping(value = "/coursesByCategory")
    public String getCoursesByCategory(@RequestParam(required = false) String category, Model model) {
        logger.info("Requested category: {}", category); // Check if the category is correct
        List<Course> courses;
        if (category != null) {
            courses = courseRepository.findByCategory(category);
            model.addAttribute("selectedCategory", category);
        } else {
            courses = courseService.getAllCourses();
            model.addAttribute("selectedCategory", "All");
        }
        model.addAttribute("courses", courses);
        model.addAttribute("categories", courseRepository.findDistinctCategories());
        return "home";
    }

    @GetMapping("/cart")
    public String showCart(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            User user  = userRepository.findByUserName(username);
            Cart cart = cartRepository.findByUser(user);
            model.addAttribute("cart", cart);
        }
        return "home";
    }
    @GetMapping(value = {"","/","/home"})
    public String home(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        model.addAttribute("categories", courseRepository.findDistinctCategories());
        return "home";
    }

}
