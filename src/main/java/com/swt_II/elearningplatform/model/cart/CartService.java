package com.swt_II.elearningplatform.model.cart;

import com.swt_II.elearningplatform.model.course.Course;
import com.swt_II.elearningplatform.model.user.User;
import com.swt_II.elearningplatform.repositories.CartRepository;
import com.swt_II.elearningplatform.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private CartRepository cartRepository;

    @Transactional
    public void addCourseToCart(User user, Course course) {
        Cart cart = cartRepository.findByUser(user);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
        }

        if (!cart.getCourses().contains(course)) {
            cart.getCourses().add(course);
        }
        cartRepository.save(cart);
    }
    @Transactional
    public void removeCourseFromCart(User user, Course course) {
        Cart cart = cartRepository.findByUser(user);
        if (cart != null && cart.getCourses().contains(course)) {
            cart.getCourses().remove(course);
            cartRepository.save(cart);
        }
    }
    public List<Course> getCartItemsForUser(User user) {
        Cart cart = cartRepository.findByUser(user);
        if (cart != null) {
            return cart.getCourses();
        } else {
            return List.of(); // Return an empty list if the cart is null
        }
    }
    public Cart getCartForUser(User user) {
        return cartRepository.findByUser(user);
    }

//
//        // Check if the course is already in the cart
//        boolean courseAlreadyInCart = cart.getCartItems().stream()
//                .anyMatch(cartItem -> cartItem.getCourse().getId().equals(course.getId()));
//
//        if (!courseAlreadyInCart) {
//            CartItem cartItem = new CartItem();
//            cartItem.setCourse(course);
//            cartItem.setCart(cart);
//            cart.getCartItems().add(cartItem);
//            logger.info("Added course {} to cart", course.getName());
//        } else {
//            logger.info("Course {} is already in the cart", course.getName());
//        }
//
//        cartRepository.save(cart);
//        logger.info("Saved cart for user {}", user.getUserName());
}
