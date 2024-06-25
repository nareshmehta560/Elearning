package com.swt_II.elearningplatform.model.course;

import com.swt_II.elearningplatform.model.cart.Cart;
import com.swt_II.elearningplatform.model.user.Instructor;
import com.swt_II.elearningplatform.model.wishlist.Wishlist;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Course{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id",nullable=false)
    private Long id;
    
    private String name;

    private String author;

    @Lob
    @Column(columnDefinition = "CLOB")
    private String description;

    private String category;

    private Double price;

    @Lob
    private byte[] field;

    private String fileName;



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Cart_Course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "cart_id"))
    private List<Cart> carts= new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Wishlist_Course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "wishlist_id"))
    private List<Wishlist> wishlists= new ArrayList<>();

}
