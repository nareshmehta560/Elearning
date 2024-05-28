package com.swt_II.elearningplatform.model.role;

import com.swt_II.elearningplatform.model.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String roleName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Collection<User> userThisRole;

    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

}
