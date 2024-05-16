package com.swt_II.elearningplatform.model.user;

import com.swt_II.elearningplatform.model.role.Role;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@lombok.Getter
@lombok.Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String userName;
    private String passWord;
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @OneToOne(mappedBy = "user")
    private Instructor instructor;


    public void addRole(Role role){
        if (this.roles == null) {
            this.roles = new ArrayList<>();
        }
        this.roles.add(role);
    }



}
