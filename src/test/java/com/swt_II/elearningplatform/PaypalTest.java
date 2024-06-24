package com.swt_II.elearningplatform;


import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
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

import java.util.Collections;

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
        Payment mockPayment = new Payment();
        mockPayment.setState("approved");

        when(paypalService.executePayment(anyString(), anyString())).thenReturn(mockPayment);

        mockMvc.perform(get("/payment/success").param("paymentId", "123").param("PayerID", "456"))
                .andExpect(status().isOk())
                .andExpect(view().name("paymentSuccess"));
    }
}

