package com.swt_II.elearningplatform;

import com.swt_II.elearningplatform.model.role.Role;
import com.swt_II.elearningplatform.model.user.User;
import com.swt_II.elearningplatform.repositories.RoleRepository;
import com.swt_II.elearningplatform.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ElearningplatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElearningplatformApplication.class, args);

	}
    @Bean
    public CommandLineRunner demo(UserRepository repository, RoleRepository roleRepo) {
        return (args) -> {

            Role userRole = new Role("USER");
            Role adminRole = new Role("ADMIN");
            userRole = roleRepo.save(userRole);
            adminRole = roleRepo.save(adminRole);
            // create a new user
            User user = new User();
            user.setUsername("testUser");
            user.setPassword("$2a$12$NZ0jhuPxZGGuCIhEkvnJr.gOghvuMbN4M3BZgWSWnOh24BBbrQ.Wy");
            user.setEmail("test1@gmail.com");
            user.addRole(userRole);
            user.addRole(adminRole);

            // save the user to the database
            repository.save(user);
        };
    }

}
