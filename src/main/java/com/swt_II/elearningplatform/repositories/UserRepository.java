package com.swt_II.elearningplatform.repositories;

import com.swt_II.elearningplatform.model.role.Role;
import com.swt_II.elearningplatform.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findByUserName(String username);

}
