package com.swt_II.elearningplatform.controller;

import com.swt_II.elearningplatform.model.cart.CartService;
import com.swt_II.elearningplatform.model.course.Course;
import com.swt_II.elearningplatform.model.course.CourseService;
import com.swt_II.elearningplatform.model.user.User;
import com.swt_II.elearningplatform.model.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
@org.springframework.web.bind.annotation.RestController
public class CartController {

    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;

    @Autowired
    private CartService cartService;


    @PostMapping("/addToCart")
    public String addToCart(@RequestBody Map<String, String> body) {
        Long courseId = Long.valueOf(body.get("courseId"));
        User user = userService.getCurrentUser();
        Course course = courseService.getCourseById(courseId);

        if (course != null) {
            cartService.addCourseToCart(user, course);
        }

        return "redirect:/home"; // Redirect to the appropriate page after adding to cart
    }
}
