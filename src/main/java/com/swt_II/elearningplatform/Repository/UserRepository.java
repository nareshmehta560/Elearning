package com.swt_II.elearningplatform.Repository;

import com.swt_II.elearningplatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByEmail(String email);
}
