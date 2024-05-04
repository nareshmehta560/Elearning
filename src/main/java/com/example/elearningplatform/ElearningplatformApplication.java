package com.example.elearningplatform;

import com.example.elearningplatform.model.course.Course;
import com.example.elearningplatform.model.role.Role;
import com.example.elearningplatform.model.user.User;
import com.example.elearningplatform.repositories.CourseRepository;
import com.example.elearningplatform.repositories.RoleRepository;
import com.example.elearningplatform.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ElearningplatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElearningplatformApplication.class, args);

	}
    @Bean
    public CommandLineRunner demo(UserRepository repository, RoleRepository roleRepo, CourseRepository courseRepository) {
        return (args) -> {
            // create a new user
            Role userRole = new Role("USER");
            Role adminRole = new Role("ADMIN");
            userRole = roleRepo.save(userRole);
            adminRole = roleRepo.save(adminRole);
            User user = new User();
            user.setUserName("testUser");
            user.setPassWord("$2a$12$NZ0jhuPxZGGuCIhEkvnJr.gOghvuMbN4M3BZgWSWnOh24BBbrQ.Wy");
            user.setEmail("test1@gmail.com");
            user.addRole(userRole);
            user.addRole(adminRole);

            // save the user to the database
            repository.save(user);

            Course course = new Course();
            course.setId(1L);
            course.setCourseName("Mathematik");
            course.setDescription("THISI IS MATHEMATIK LOOLLL");
            courseRepository.save(course);
        };
    }

}
