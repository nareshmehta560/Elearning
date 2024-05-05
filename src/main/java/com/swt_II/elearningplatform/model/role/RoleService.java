package com.swt_II.elearningplatform.model.role;

import com.swt_II.elearningplatform.model.user.User;
import com.swt_II.elearningplatform.repositories.RoleRepository;
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
