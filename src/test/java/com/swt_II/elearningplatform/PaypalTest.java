package com.swt_II.elearningplatform;


import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.swt_II.elearningplatform.model.cart.Cart;
import com.swt_II.elearningplatform.model.cart.CartService;
import com.swt_II.elearningplatform.model.course.Course;
import com.swt_II.elearningplatform.model.user.User;
import com.swt_II.elearningplatform.model.user.UserService;
import com.swt_II.elearningplatform.paypal.PaypalController;
import com.swt_II.elearningplatform.paypal.PaypalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class PaypalTest {

    private MockMvc mockMvc;

    @MockBean
    private PaypalService paypalService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private UserService userService;

    @MockBean
    private CartService cartService;

    @InjectMocks
    private PaypalController paypalController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testHome() throws Exception {
        mockMvc.perform(get("/pay").param("amount", "10.00"))
                .andExpect(status().isOk())
                .andExpect(view().name("paypal"))
                .andExpect(model().attribute("amount", "10.00"));
    }

    @Test
    public void testCreatePayment() throws Exception {
        Payment mockPayment = new Payment();
        Links approvalLink = new Links();
        approvalLink.setRel("approval_url");
        approvalLink.setHref("http://approval_url");
        mockPayment.setLinks(Collections.singletonList(approvalLink));

        when(paypalService.createPayment(anyDouble(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString()))
                .thenReturn(mockPayment);

        mockMvc.perform(post("/payment/create").param("amount", "10.00"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://approval_url"));
    }
    //Test for paypal payment success
    @Test
    public void testPaymentSuccess() throws Exception {
        // Mock the Payment
        Payment mockPayment = new Payment();
        mockPayment.setState("approved");

        // Mock the User
        User mockUser = new User();
        mockUser.setCourses(new HashSet<>());

        // Mock the Cart
        Cart mockCart = new Cart();
        mockCart.setCourses(new ArrayList<>());

        // Mock the Course
        Course mockCourse = new Course();
        List<Course> mockCourses = Collections.singletonList(mockCourse);

        // Define the behavior of your mocks
        when(paypalService.executePayment(anyString(), anyString())).thenReturn(mockPayment);
        when(userService.getCurrentUser()).thenReturn(mockUser);
        when(cartService.getCartItemsForUser(mockUser)).thenReturn(mockCourses);
        when(cartService.getCartForUser(mockUser)).thenReturn(mockCart);

        // Perform the request
        mockMvc.perform(get("/payment/success").param("paymentId", "123").param("PayerID", "456"))
                .andExpect(status().isOk())
                .andExpect(view().name("paymentSuccess"));

        // Verify that saveUser was called
        verify(userService, times(1)).saveUser(mockUser);
    }
    // test for payment error
    @Test
    public void testPaymentError() throws Exception {
        mockMvc.perform(get("/payment/error"))
                .andExpect(status().isOk())
                .andExpect(view().name("paymentError"));
    }
    // test for payment cancel
    @Test
    public void testPaymentCancel() throws Exception {
        mockMvc.perform(get("/payment/cancel"))
                .andExpect(status().isOk())
                .andExpect(view().name("paymentCancel"));
    }


}

