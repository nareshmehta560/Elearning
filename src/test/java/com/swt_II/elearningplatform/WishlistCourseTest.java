package com.swt_II.elearningplatform;

import com.swt_II.elearningplatform.model.course.Course;
import com.swt_II.elearningplatform.model.user.User;
import com.swt_II.elearningplatform.model.wishlist.Wishlist;
import com.swt_II.elearningplatform.model.wishlist.WishlistService;
import com.swt_II.elearningplatform.repositories.WishlistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class WishlistCourseTest {

    @Mock
    private WishlistRepository wishlistRepository;

    @InjectMocks
    private WishlistService wishlistService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddCourseToWishlist() {
        User user = new User();
        Course course = new Course();
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);

        when(wishlistRepository.findByUser(user)).thenReturn(wishlist);

        wishlistService.addCourseToWishlist(user, course);

        verify(wishlistRepository, times(1)).save(wishlist);
        assertEquals(1, wishlist.getCourses().size());
    }

}
