package com.swt_II.elearningplatform;

import com.swt_II.elearningplatform.repositories.RoleRepository;
import com.swt_II.elearningplatform.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;



    @BeforeEach
    public void setup() {
        userRepo.deleteAll();
        roleRepo.deleteAll();
        // Setup initial roles or other necessary preconditions if needed
    }

    @ParameterizedTest
    @MethodSource("provideRegistrationData")
    public void testRegistration(String firstName, String lastName, String username, String email, String password, String confirmPassword, int expectedStatus, String expectedView) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .param("firstName", firstName)
                        .param("lastName", lastName)
                        .param("username", username)
                        .param("email", email)
                        .param("password", password)
                        .param("confirmPassword", confirmPassword)
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().is(expectedStatus))
                .andExpect(view().name(expectedView));
    }

    private static Stream<Arguments> provideRegistrationData() {
        return Stream.of(
                Arguments.of("Alex", "Yamav", "alex10", "yamavalex10@gmail.com", "asdfghj12@", "asdfghj12@", 200, "customLogin") // Successful registration
        );
    }

    @Test
    public void testRegistrationPageLoads() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("user"));
    }
}
