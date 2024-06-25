package com.swt_II.elearningplatform;
import com.swt_II.elearningplatform.model.cart.Cart;
import com.swt_II.elearningplatform.model.cart.CartService;
import com.swt_II.elearningplatform.model.course.Course;
import com.swt_II.elearningplatform.model.user.User;
import com.swt_II.elearningplatform.repositories.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CartCourseTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddCourseToCart() {
        User user = new User();
        Course course = new Course();
        Cart cart = new Cart();
        cart.setUser(user);

        when(cartRepository.findByUser(user)).thenReturn(cart);

        cartService.addCourseToCart(user, course);

        verify(cartRepository, times(1)).save(cart);
        assertEquals(1, cart.getCourses().size());
    }
    @Test
    public void testRemoveCourseFromCart() {
        User user = new User();
        Course course = new Course();
        Cart cart = new Cart();
        cart.setUser(user);
        cart.getCourses().add(course);

        when(cartRepository.findByUser(user)).thenReturn(cart);

        cartService.removeCourseFromCart(user, course);

        verify(cartRepository, times(1)).save(cart);
        assertEquals(0, cart.getCourses().size());
    }

    @Test
    public void testGetCartItemsForUser() {
        User user = new User();
        Course course = new Course();
        Cart cart = new Cart();
        cart.setUser(user);
        cart.getCourses().add(course);

        when(cartRepository.findByUser(user)).thenReturn(cart);

        List<Course> courses = cartService.getCartItemsForUser(user);

        assertEquals(1, courses.size());
        assertEquals(course, courses.get(0));
    }

    @Test
    public void testAddCourseToCartDuplicate() {
        User user = new User();
        Course course = new Course();
        course.setId(1L);  // Setzen Sie eine ID, um die Gleichheit zu überprüfen
        Cart cart = new Cart();
        cart.setUser(user);
        cart.getCourses().add(course);

        when(cartRepository.findByUser(user)).thenReturn(cart);

        cartService.addCourseToCart(user, course);

        // Verifizieren, dass `save` genau einmal aufgerufen wird, obwohl wir zweimal hinzufügen versuchen
        verify(cartRepository, times(1)).save(cart);
        // Der Kurs sollte nur einmal im Warenkorb sein
        assertEquals(1, cart.getCourses().size());
    }
}
