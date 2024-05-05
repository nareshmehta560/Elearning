package com.example.elearningplatform.Repository;

import com.example.elearningplatform.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
