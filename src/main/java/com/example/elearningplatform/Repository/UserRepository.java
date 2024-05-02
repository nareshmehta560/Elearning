package com.example.elearningplatform.Repository;

import com.example.elearningplatform.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
