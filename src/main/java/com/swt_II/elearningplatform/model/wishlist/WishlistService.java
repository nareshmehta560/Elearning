package com.swt_II.elearningplatform.model.wishlist;

import com.swt_II.elearningplatform.repositories.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {
    private static WishlistRepository wishlistRepository;

    @Autowired
    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public static Wishlist getWishlistForUser(String userName) {
        return wishlistRepository.findByUser_UserName(userName); // Assuming the method in the repository
    }
}
