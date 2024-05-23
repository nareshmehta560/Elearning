package com.swt_II.elearningplatform.repositories;

import com.swt_II.elearningplatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
