package com.studentms.security;

import com.studentms.model.User;
import com.studentms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmailOrUsername(username, username).orElseThrow(
                () -> new UsernameNotFoundException("User "+username+" not found !")
        );

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), List.of(()->"Read"));
    }
}
