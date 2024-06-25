package com.swt_II.elearningplatform.controller;

import com.swt_II.elearningplatform.model.course.Course;
import com.swt_II.elearningplatform.model.course.CourseService;
import com.swt_II.elearningplatform.model.user.User;
import com.swt_II.elearningplatform.model.user.UserService;
import com.swt_II.elearningplatform.model.wishlist.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
public class WishlistController {
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/addToWishlist")
    public String addToWishlist(@RequestBody Map<String, String> body) {
        Long courseId = Long.valueOf(body.get("courseId"));
        User user = userService.getCurrentUser();
        Course course = courseService.getCourseById(courseId);

        if (course != null) {
            wishlistService.addCourseToWishlist(user, course);
        }

        return "redirect:/home"; // Redirect to the appropriate page after adding to cart
    }
    @PostMapping("/removeFromWishlist")
    public String removeFromWishlist(@RequestBody Map<String, String> body) {
        User user = userService.getCurrentUser();
        String courseIdStr = body.get("courseId");

        if (courseIdStr != null && !courseIdStr.isEmpty()) {
            try {
                Long courseId = Long.valueOf(courseIdStr);
                Course course = courseService.getCourseById(courseId);

                if (course != null) {
                    wishlistService.removeCourseFromWishlist(user, course);
                    return "Course removed from wishlist successfully.";
                } else {
                    return "Course not found.";
                }
            } catch (NumberFormatException e) {
                return "Invalid course Id format.";
            }
        } else {
            return "Course Id is required.";
        }
    }

    @GetMapping(value = "/getWishlistItems", produces = "application/json")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Course> getWishlistItems() {
        User user = userService.getCurrentUser();
        return wishlistService.getWishlistItemsForUser(user);
    }
}
