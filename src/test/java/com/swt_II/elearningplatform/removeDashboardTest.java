package com.swt_II.elearningplatform;

import com.swt_II.elearningplatform.repositories.RoleRepository;
import com.swt_II.elearningplatform.repositories.UserRepository;
import jakarta.servlet.ServletContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.SessionProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

@SpringBootTest
@AutoConfigureMockMvc
public class removeDashboardTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Test
    public void testDashboardUrl() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/dashboard"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }


    @Test
    public void testDashboardUrlLoggedIn() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/dashboard")
                        .with(SecurityMockMvcRequestPostProcessors.user("testUser")))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
