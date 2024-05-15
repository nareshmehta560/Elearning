package com.swt_II.elearningplatform.security;

import com.swt_II.elearningplatform.model.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().requestMatchers("/h2-console/**","/webjars/**",
                "/resources/static/**","/css/**"));
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder());
        auth.setUserDetailsService(userService);
        return auth;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer -> {
            configurer
                    .requestMatchers("/","/home","/customLogin","registration", "/courseupload").permitAll()
                    .requestMatchers("/dashboard","/admin").hasAnyRole("ADMIN","USER")
                    .anyRequest().authenticated();
        }).logout(LogoutConfigurer::permitAll)
                .formLogin(form -> form.loginPage("/customLogin")
                        .loginProcessingUrl("/performLogin")
                        .defaultSuccessUrl("/dashboard")
                        .permitAll());
        return http.build();
    }
}
