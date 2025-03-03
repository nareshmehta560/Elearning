package com.swt_II.elearningplatform.security;

import com.swt_II.elearningplatform.model.user.User;
import com.swt_II.elearningplatform.model.user.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class CustomFailureHandler implements AuthenticationFailureHandler {
    @Autowired
    private UserService userService;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        User user = userService.getCurrentUser();
        userService.increaseFailedAttempts(userService.getCurrentUser());
        if (user.getFailedLoginAttempts() >= 5) {
            userService.lock(user);
            userService.saveUser(user);
        }

        response.sendRedirect("/login?error");
    }
}
