package com.swt_II.elearningplatform.repositories;

import com.swt_II.elearningplatform.model.cart.Cart;
import com.swt_II.elearningplatform.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    //Cart findByUser_UserName(String userName);

    Cart findByUser(User user);
}
