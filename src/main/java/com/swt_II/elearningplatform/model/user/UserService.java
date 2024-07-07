package com.swt_II.elearningplatform.model.user;

import com.swt_II.elearningplatform.model.course.Course;
import com.swt_II.elearningplatform.model.role.Role;
import com.swt_II.elearningplatform.model.role.RoleService;
import com.swt_II.elearningplatform.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),mapRolesToAuthorities(roleService.getRolesFromUserId(user)));

    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName())).collect(Collectors.toList());
    }
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        return userRepository.findByUserName(authentication.getName());
    }
    public void saveUser(User user) {
        userRepository.save(user);
    }
    public Set<Course> getEnrolledCourses() {
        User user = getCurrentUser();
        return  user.getCourses();
    }

}
