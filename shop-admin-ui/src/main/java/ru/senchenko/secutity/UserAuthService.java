package ru.senchenko.secutity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.senchenko.repositories.UserRepo;

import java.util.Collections;


@Service
@Transactional(readOnly = true)
public class UserAuthService implements UserDetailsService {

    private final UserRepo userRepo;

    @Autowired
    public UserAuthService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findUserByUserName(username)
                .map(u -> new org.springframework.security.core.userdetails.User(
                        u.getUserName(),
                        u.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority("USER"))))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
