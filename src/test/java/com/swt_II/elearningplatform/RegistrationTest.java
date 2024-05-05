package com.swt_II.elearningplatform;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @MethodSource("testRegistration")
    @WithMockUser(username="alex10", password="asdfghj12@", roles="USER")
    public void testRegistration(String firstName, String lastName, String username, String email, String password, String confirmPassword, int expectedStatus) throws Exception{
        mockMvc.perform(post("/register")
                .param("firstName", firstName)
                .param("lastName", lastName)
                .param("username", username)
                .param("email", email)
                .param("password", password)
                .param("confirmPassword", confirmPassword))
            .andExpect(status().is(expectedStatus));
    }
    private static Stream<Arguments> testRegistration() {
        return Stream.of(
                Arguments.of("Alex", "Yamav", "alex10", "yamavalex10@gmail.com", "asdfghj12@", "asdfghj12@", 200)
        );
    }
}
