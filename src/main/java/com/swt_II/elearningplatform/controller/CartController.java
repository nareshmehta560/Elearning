package com.swt_II.elearningplatform.controller;

import com.swt_II.elearningplatform.model.cart.CartService;
import com.swt_II.elearningplatform.model.course.Course;
import com.swt_II.elearningplatform.model.course.CourseService;
import com.swt_II.elearningplatform.model.user.User;
import com.swt_II.elearningplatform.model.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
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
    @PostMapping("/removeFromCart")
    public String removeFromCart(@RequestBody Map<String, String> body) {
        User user = userService.getCurrentUser();
        String courseIdStr = body.get("courseId");

        if (courseIdStr != null && !courseIdStr.isEmpty()) {
            try {
                Long courseId = Long.valueOf(courseIdStr);
                Course course = courseService.getCourseById(courseId);

                if (course != null) {
                    cartService.removeCourseFromCart(user, course);
                    return "Course removed from cart successfully.";
                } else {
                    return "Course not found.";
                }
            } catch (NumberFormatException e) {
                return "Invalid courseId format.";
            }
        } else {
            return "courseId is required.";
        }
    }

    @GetMapping(value = "/getCartItems", produces = "application/json")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Course> getCartItems() {
        User user = userService.getCurrentUser();
        return cartService.getCartItemsForUser(user);
    }


}
