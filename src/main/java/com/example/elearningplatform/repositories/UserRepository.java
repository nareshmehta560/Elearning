package com.example.elearningplatform.repositories;

import com.example.elearningplatform.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findByUserName(String username);
}
