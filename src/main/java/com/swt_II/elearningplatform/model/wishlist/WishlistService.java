package com.swt_II.elearningplatform.model.wishlist;

import com.swt_II.elearningplatform.model.course.Course;
import com.swt_II.elearningplatform.model.user.User;
import com.swt_II.elearningplatform.repositories.WishlistRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {
    private static WishlistRepository wishlistRepository;

    @Autowired
    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }


    @Transactional
    public void addCourseToWishlist(User user, Course course) {
       Wishlist wishlist = wishlistRepository.findByUser(user);
        if (wishlist == null) {
            wishlist = new Wishlist();
            wishlist.setUser(user);
        }

        if (!wishlist.getCourses().contains(course)) {
            wishlist.getCourses().add(course);
        }
        wishlistRepository.save(wishlist);
    }
    @Transactional
    public void removeCourseFromWishlist(User user, Course course) {
        Wishlist wishlist = wishlistRepository.findByUser(user);
        if (wishlist != null && wishlist.getCourses().contains(course)) {
            wishlist.getCourses().remove(course);
            wishlistRepository.save(wishlist);
        }
    }
    public List<Course> getCartItemsForUser(User user) {
        Wishlist wishlist = wishlistRepository.findByUser(user);
        if (wishlist != null) {
            return wishlist.getCourses();
        } else {
            return List.of(); // Return an empty list if the wishlist is null
        }
    }
}
