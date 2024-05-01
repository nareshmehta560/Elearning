package com.example.elearningplatform.model.role;

import com.example.elearningplatform.model.user.User;
import com.example.elearningplatform.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleService {
    @Autowired
    private final RoleRepository roleRepo;

    public RoleService(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    public Collection<Role> getRolesFromUserId(User user) {
        return roleRepo.findAllByUserThisRoleContaining(user);
    }

    public Role getRoleFromRoleName(String roleName) {
        return roleRepo.findByRoleName(roleName);
    }

}
