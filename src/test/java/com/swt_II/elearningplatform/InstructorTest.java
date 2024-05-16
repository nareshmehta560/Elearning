package com.swt_II.elearningplatform;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class InstructorTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;



    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }
    @Test
    @WithUserDetails("testUser2")
    public void givenNonAdminUser_whenGetNewInstructors_thenForbidden() throws Exception {
        mockMvc.perform(get("/newInstructors"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("testUser")
    public void givenAdminUser_whenGetNewInstructors_thenOk() throws Exception {
        mockMvc.perform(get("/newInstructors"))
                .andExpect(status().isOk());
    }


}