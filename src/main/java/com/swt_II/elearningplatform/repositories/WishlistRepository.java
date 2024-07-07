package com.swt_II.elearningplatform.repositories;

import com.swt_II.elearningplatform.model.wishlist.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Wishlist findByUser(String username);

}
