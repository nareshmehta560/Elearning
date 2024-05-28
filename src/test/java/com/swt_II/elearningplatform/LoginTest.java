package com.swt_II.elearningplatform;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
 import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.stream.Stream;

 import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {

        @Autowired
        private MockMvc mockMvc;

        @ParameterizedTest
        @MethodSource("provideLoginData")
        @WithMockUser(username = "testUser", password = "testPassword")
        public void testLogin(String username, String password, int expectedStatus) throws Exception {

                    mockMvc.perform(formLogin("/performLogin").user(username).password(password));
        }

        private static Stream<Arguments> provideLoginData() {
            return Stream.of(
                    Arguments.of("testUser", "usertest", 200),
                    Arguments.of("wrongUser", "wrongPassword", 401)
            );
        }

        @Test
        public void testLoginFormDisplayed() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/customLogin"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.view().name("customLogin"));

        }
    }
