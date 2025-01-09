package com.github.socceronlinemanagerapi.security;

import com.github.socceronlinemanagerapi.db.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

@Configuration
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new User(user.getEmail(), user.getPassword(), List.of());
    }
}
