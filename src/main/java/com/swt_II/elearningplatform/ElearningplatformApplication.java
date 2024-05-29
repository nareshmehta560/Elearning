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
            if (!roleRepo.existsRoleByRoleName("USER")) {
                Role userRole = new Role("USER");
                Role adminRole = new Role("ADMIN");
                Role instructorRole = new Role("INSTRUCTOR");
                userRole = roleRepo.save(userRole);
                adminRole = roleRepo.save(adminRole);
                instructorRole = roleRepo.save(instructorRole);

                // create a new user with admin and user right
                if (!userRepository.existsUserByUserName("testUser")) {
                    User admin = new User();
                    admin.setUserName("testUser");
                    admin.setPassword("$2a$12$NZ0jhuPxZGGuCIhEkvnJr.gOghvuMbN4M3BZgWSWnOh24BBbrQ.Wy");
                    admin.setEmail("test1@gmail.com");
                    admin.addRole(userRole);
                    admin.addRole(adminRole);
                    userRepository.save(admin);
                }

                /* create a new instructor who is admin


                 Instructor instructor = new Instructor("MSc", "5 years");
                instructor.setPaypalEmail("test@gmail.com");
                User user1 = userRepository.findByUserName("testUser");
                instructor.setUser(user1);
                instructorRepository.save(instructor);

                 */


                //create a another with only user Right
                if (!userRepository.existsUserByUserName("testUser2")) {
                    User user = new User();
                    user.setUserName("testUser2");
                    user.setPassword("$2a$12$yI8wA5Kd0pcFknDHqyZgN.9/wAHmVzLRCpsBNuUiCSLazDJ4tYM8u");
                    user.setEmail("test@gmail.com");
                    user.addRole(userRole);
                    userRepository.save(user);
                }
            }

        };
    }

}
