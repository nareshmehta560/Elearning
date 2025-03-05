package com.swt_II.elearningplatform.model.user;

import com.swt_II.elearningplatform.model.course.Course;
import com.swt_II.elearningplatform.model.role.Role;
import com.swt_II.elearningplatform.model.role.RoleService;
import com.swt_II.elearningplatform.repositories.UserRepository;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private static final long LOCK_TIME_DURATION = 1000 * 60 * 1;// time in milliseconds
    private static final int MAX_FAILED_ATTEMPTS = 3;

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
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),true,true,true,user.isAccountNonLocked(),mapRolesToAuthorities(roleService.getRolesFromUserId(user)));

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
    public User findUserByUserName(String username) {
        return userRepository.findByUserName(username);
    }
    public void saveUser(User user) {
        userRepository.save(user);
    }
    public Set<Course> getEnrolledCourses() {
        User user = getCurrentUser();
        return  user.getCourses();
    }
    public void increaseFailedAttempts(User user) {
        user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);
        if (user.getFailedLoginAttempts() >= MAX_FAILED_ATTEMPTS) {
            lock(user);
        }
        saveUser(user);

    }
    public void lock(User user) {
        user.setAccountNonLocked(false);
        user.setLockTime(LocalDateTime.now());
        saveUser(user);
    }
    public boolean unlock(User user) {
        int failedAttempts = user.getFailedLoginAttempts();
        LocalDateTime lockTime = user.getLockTime();
        LocalDateTime currentTime = LocalDateTime.now();
        long lockDuration = (long) (LOCK_TIME_DURATION * (Math.pow(2, failedAttempts - MAX_FAILED_ATTEMPTS))) / 1000 / 60;

        if (lockTime.plusMinutes(lockDuration).isBefore(currentTime)) {
            user.setAccountNonLocked(true);
            user.setFailedLoginAttempts(0);
            saveUser(user);
            return true;
        }
        return false;
    }
    @Scheduled(fixedRate = 1 * 60 * 1000) // refresh every 5 minute
    public void unlockUsers() {
        List<User> lockedUsers = userRepository.findAllByAccountNonLocked(false);
        for (User user : lockedUsers) {
            unlock(user);
        }
    }

}
