package com.swt_II.elearningplatform.model.role;

import com.swt_II.elearningplatform.model.user.User;
import com.swt_II.elearningplatform.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepo;

    public RoleService(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }


    public List<Role> getRolesFromUserId(User user) {
        return roleRepo.findAllByUserThisRoleContaining(user);
    }

    public Role getRoleFromRoleName(String roleName) {
        return roleRepo.findByRoleName(roleName);
    }

}
