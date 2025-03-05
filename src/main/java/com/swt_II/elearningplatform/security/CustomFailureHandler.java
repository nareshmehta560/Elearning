package com.swt_II.elearningplatform.security;

import com.swt_II.elearningplatform.model.user.User;
import com.swt_II.elearningplatform.model.user.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class CustomFailureHandler implements AuthenticationFailureHandler {

    private UserService userService;

    @Autowired
    private CustomSuccessHandler successHandler;

    public CustomFailureHandler(UserService userService) {
        this.userService = userService;
    }
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        User user = userService.findUserByUserName(username);

        if (user != null) {
            if (user.isAccountNonLocked()) {
                userService.increaseFailedAttempts(user);
            } else {
                    response.sendRedirect("/customLogin?blocked");
                    return;
                }

            }
             response.sendRedirect("/customLogin?error");
        }



}
