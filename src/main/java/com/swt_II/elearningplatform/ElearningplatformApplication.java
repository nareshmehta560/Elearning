package com.swt_II.elearningplatform;

import com.swt_II.elearningplatform.model.role.Role;
import com.swt_II.elearningplatform.model.user.Instructor;
import com.swt_II.elearningplatform.model.user.User;
import com.swt_II.elearningplatform.repositories.InstructorRepository;
import com.swt_II.elearningplatform.repositories.RoleRepository;
import com.swt_II.elearningplatform.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ElearningplatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElearningplatformApplication.class, args);

	}

    @Bean
    @Profile("dev")
    public CommandLineRunner demo(RoleRepository roleRepo, InstructorRepository instructorRepository, UserRepository userRepository) {
        return (args) -> {
            // create a new role

        };
    }
}